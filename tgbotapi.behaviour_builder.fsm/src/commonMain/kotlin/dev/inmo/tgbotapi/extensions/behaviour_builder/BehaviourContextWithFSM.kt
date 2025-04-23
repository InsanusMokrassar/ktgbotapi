package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.micro_utils.fsm.common.*
import dev.inmo.micro_utils.fsm.common.utils.StateHandlingErrorHandler
import dev.inmo.micro_utils.fsm.common.utils.defaultStateHandlingErrorHandler
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextWithFSM.Companion.DATA_FSM_KEY
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar.TriggersHolder
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.reflect.KClass

/**
 * Interface which combine [BehaviourContext] and [StatesMachine]. Subcontext of triggers and states contexts must have
 * one common flow of updates and must not lose updates between updates
 *
 * @see DefaultBehaviourContextWithFSM
 * @see buildBehaviourWithFSM
 */
interface BehaviourContextWithFSM<T : State> : BehaviourContext, StatesMachine<T> {
    /**
     * Will be called BEFORE handling of [State] will be started
     */
    val stateInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContextWithFSM<T>, Unit, T>

    suspend fun start() = start(this)

    /**
     * Add NON STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Non strict means that
     * for input [State] will be used [KClass.isInstance] and any inheritor of [kClass] will pass this requirement
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see onStateOrSubstate
     */
    fun <I : T> add(
        kClass: KClass<I>,
        strict: Boolean = false,
        handler: BehaviourWithFSMStateHandler<I, T>,
    )

    /**
     * Add STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Strict means that
     * for input [State] will be used [State]::class == [kClass] and any [State] with exactly the same type will pass
     * requirements
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see strictlyOn
     */
    fun <I : T> addStrict(
        kClass: KClass<I>,
        handler: BehaviourWithFSMStateHandler<I, T>,
    ) = add(kClass, strict = true, handler)

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        triggersHolder: TriggersHolder,
        subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>,
    ): BehaviourContextWithFSM<T>

    fun copy(
        bot: TelegramBot = this.bot,
        scope: CoroutineScope = this.scope,
        broadcastChannelsSize: Int = 100,
        onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
        upstreamUpdatesFlow: Flow<Update>? = null,
        subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {},
        triggersHolder: TriggersHolder = this.triggersHolder,
        stateInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContextWithFSM<T>, Unit, T> = this.stateInitialAction,
        onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
    ): BehaviourContextWithFSM<T>

    companion object {
        operator fun <T : State> invoke(
            behaviourContext: BehaviourContext,
            handlers: List<BehaviourWithFSMStateHandlerHolder<*, T>>,
            statesManager: StatesManager<T>,
            fallbackHandler: BehaviourWithFSMStateHandlerHolder<T, T>? = null,
            stateInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContextWithFSM<T>, Unit, T> = {},
            onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
        ) = DefaultBehaviourContextWithFSM<T>(
            behaviourContext,
            statesManager,
            handlers,
            fallbackHandler,
            stateInitialAction,
            onStateHandlingErrorHandler,
        )

        val DATA_FSM_KEY = "ktgbotapi_fsm"
    }
}

/**
 * Add NON STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Non strict means that
 * for input [State] will be used [KClass.isInstance] and any inheritor of [kClass] will pass this requirement
 *
 * @see BehaviourWithFSMStateHandlerHolder
 * @see BehaviourContextWithFSM.add
 */
@Suppress("MemberVisibilityCanBePrivate")
inline fun <reified I : O, O : State> BehaviourContextWithFSM<O>.onStateOrSubstate(handler: BehaviourWithFSMStateHandler<I, O>) = add(I::class, strict = false, handler)

/**
 * Add STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Strict means that
 * for input [State] will be used [State]::class == [kClass] and any [State] with exactly the same type will pass
 * requirements
 *
 * @see BehaviourWithFSMStateHandlerHolder
 * @see BehaviourContextWithFSM.addStrict
 */
@Suppress("MemberVisibilityCanBePrivate")
inline fun <reified I : O, O : State> BehaviourContextWithFSM<O>.strictlyOn(handler: BehaviourWithFSMStateHandler<I, O>) = addStrict(I::class, handler)

/**
 * Default realization of [BehaviourContextWithFSM]. It uses [behaviourContext] as a base for this object as
 * [BehaviourContext], but managing substates contexts updates for avoiding of updates lost between states
 * @param onStateHandlingErrorHandler Will be used in case if state handling has not been successfully completed in [launchStateHandling]
 */
class DefaultBehaviourContextWithFSM<T : State>(
    private val behaviourContext: BehaviourContext,
    private val statesManager: StatesManager<T>,
    private val handlers: List<BehaviourWithFSMStateHandlerHolder<*, T>>,
    private val fallbackHandler: BehaviourWithFSMStateHandlerHolder<T, T>? = null,
    override val stateInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContextWithFSM<T>, Unit, T> = {},
    private val onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
) : BehaviourContext by behaviourContext, BehaviourContextWithFSM<T> {
    private val updatesFlows = mutableMapOf<Any, DefaultBehaviourContextWithFSM<T>>()
    private val additionalHandlers = mutableListOf<BehaviourWithFSMStateHandlerHolder<*, T>>()
    private var actualHandlersList = additionalHandlers + handlers + listOfNotNull(fallbackHandler)

    protected val statesJobs = mutableMapOf<T, Job>()
    protected val statesJobsMutex = Mutex()

    init {
        data[DATA_FSM_KEY] = this
    }

    override suspend fun launchStateHandling(
        state: T,
        handlers: List<CheckableHandlerHolder<in T, T>>,
    ): T? {
        return launchStateHandling(state, handlers, onStateHandlingErrorHandler)
    }

    private fun getSubContext(context: Any) = updatesFlows.getOrPut(context) {
        createSubContext()
    }

    override suspend fun StatesMachine<in T>.handleState(state: T): T? {
        return getSubContext(
            state.context,
        ).apply {
            stateInitialAction(state)
        }.run {
            launchStateHandling(
                state,
                actualHandlersList,
            )
        }
    }

    override fun <I : T> add(
        kClass: KClass<I>,
        strict: Boolean,
        handler: BehaviourWithFSMStateHandler<I, T>,
    ) {
        additionalHandlers.add(BehaviourWithFSMStateHandlerHolder(kClass, strict, handler))
        actualHandlersList = additionalHandlers + handlers
    }

    override fun start(scope: CoroutineScope): Job = scope.launchSafelyWithoutExceptions {
        val statePerformer: suspend (T) -> Unit = { state: T ->
            val newState = getSubContext(state.context).launchStateHandling(state, actualHandlersList)
            if (newState != null) {
                statesManager.update(state, newState)
            } else {
                statesManager.endChain(state)
            }
        }

        fun Job.enableRemoveOnCompletion(state: T) {
            invokeOnCompletion {
                launchSafelyWithoutExceptions {
                    statesJobsMutex.withLock {
                        if (this@enableRemoveOnCompletion === statesJobs[state]) {
                            statesJobs.remove(state)
                        }
                    }
                }
            }
        }

        statesManager.onStartChain.subscribeSafelyWithoutExceptions(this) {
            statesJobsMutex.withLock {
                runCatchingSafely { statesJobs.remove(it) ?.cancel() }

                statesJobs[it] = launch { statePerformer(it) }.apply { enableRemoveOnCompletion(it) }
            }
        }
        statesManager.onEndChain.subscribeSafelyWithoutExceptions(this) {
            statesJobsMutex.withLock {
                runCatchingSafely { statesJobs.remove(it) ?.cancel() }
            }
            updatesFlows.remove(it.context) ?.cancel()
        }
        statesManager.onChainStateUpdated.subscribeSafelyWithoutExceptions(this) { (old, new) ->
            statesJobsMutex.withLock {
                runCatchingSafely { statesJobs.remove(old) ?.cancel() }
                runCatchingSafely { statesJobs.remove(new) ?.cancel() }
                statesJobs[new] = launch { statePerformer(new) }.apply { enableRemoveOnCompletion(new) }
            }
            if (old.context != new.context) {
                updatesFlows.remove(old.context) ?.cancel()
            }
        }

        statesManager.getActiveStates().forEach {
            statesJobsMutex.withLock {
                runCatchingSafely { statesJobs.remove(it) ?.cancel() }

                statesJobs[it] = launch { statePerformer(it) }.apply { enableRemoveOnCompletion(it) }
            }
        }
    }

    /**
     * Add NON STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Non strict means that
     * for input [State] will be used [KClass.isInstance] and any inheritor of [kClass] will pass this requirement
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see BehaviourContextWithFSM.add
     */
    @Suppress("MemberVisibilityCanBePrivate")
    inline fun <reified I : T> onStateOrSubstate(handler: BehaviourWithFSMStateHandler<I, T>) = add(I::class, strict = false, handler)

    /**
     * Add STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Strict means that
     * for input [State] will be used [State]::class == [kClass] and any [State] with exactly the same type will pass
     * requirements
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see BehaviourContextWithFSM.addStrict
     */
    @Suppress("MemberVisibilityCanBePrivate")
    inline fun <reified I : T> strictlyOn(handler: BehaviourWithFSMStateHandler<I, T>) = addStrict(I::class, handler)

    override suspend fun startChain(state: T) {
        statesManager.startChain(state)
    }

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        triggersHolder: TriggersHolder,
        subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>,
    ): DefaultBehaviourContextWithFSM<T> = BehaviourContextWithFSM(
        behaviourContext = behaviourContext.copy(
            bot = bot,
            scope = scope,
            broadcastChannelsSize = broadcastChannelsSize,
            onBufferOverflow = onBufferOverflow,
            upstreamUpdatesFlow = upstreamUpdatesFlow,
            subcontextInitialAction = subcontextInitialAction,
            triggersHolder = triggersHolder,
        ),
        handlers = handlers,
        statesManager = statesManager,
        fallbackHandler = fallbackHandler,
        onStateHandlingErrorHandler = onStateHandlingErrorHandler,
        stateInitialAction = stateInitialAction,
    )

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>,
        triggersHolder: TriggersHolder,
        stateInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContextWithFSM<T>, Unit, T>,
        onStateHandlingErrorHandler: StateHandlingErrorHandler<T>,
    ): BehaviourContextWithFSM<T> = BehaviourContextWithFSM(
        behaviourContext = behaviourContext.copy(
            bot = bot,
            scope = scope,
            broadcastChannelsSize = broadcastChannelsSize,
            onBufferOverflow = onBufferOverflow,
            upstreamUpdatesFlow = upstreamUpdatesFlow,
            subcontextInitialAction = subcontextInitialAction,
            triggersHolder = triggersHolder,
        ),
        handlers = handlers,
        statesManager = statesManager,
        fallbackHandler = fallbackHandler,
        stateInitialAction = stateInitialAction,
        onStateHandlingErrorHandler = onStateHandlingErrorHandler,
    )

    fun fsm() = this
}

/**
 * Extracting from [BehaviourContext.data] exists [StatesMachine] by key [DATA_FSM_KEY], which usually some [BehaviourContextWithFSM].
 * In case if value absent in [BehaviourContext.data] will return null
 */
fun <T : State> BehaviourContext.fsmOrNull(): StatesMachine<T>? = data[DATA_FSM_KEY] as? StatesMachine<T>

/**
 * Extracting from [BehaviourContext.data] exists [StatesMachine] by key [DATA_FSM_KEY], which usually some [BehaviourContextWithFSM].
 * In case if value absent in [BehaviourContext.data] will throw [NullPointerException]
 *
 * @throws NullPointerException
 */
fun <T : State> BehaviourContext.fsmOrThrow(): StatesMachine<T> = fsmOrNull<T>()!!
