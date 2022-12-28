package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.micro_utils.fsm.common.*
import dev.inmo.micro_utils.fsm.common.utils.StateHandlingErrorHandler
import dev.inmo.micro_utils.fsm.common.utils.defaultStateHandlingErrorHandler
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar.TriggersHolder
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
    suspend fun start() = start(this)

    /**
     * Add NON STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Non strict means that
     * for input [State] will be used [KClass.isInstance] and any inheritor of [kClass] will pass this requirement
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see onStateOrSubstate
     */
    fun <I : T> add(kClass: KClass<I>, strict: Boolean = false, handler: BehaviourWithFSMStateHandler<I, T>)

    /**
     * Add STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Strict means that
     * for input [State] will be used [State]::class == [kClass] and any [State] with exactly the same type will pass
     * requirements
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see strictlyOn
     */
    fun <I : T> addStrict(kClass: KClass<I>, handler: BehaviourWithFSMStateHandler<I, T>) = add(kClass, strict = true, handler)

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        triggersHolder: TriggersHolder
    ): BehaviourContextWithFSM<T>

    fun copy(
        bot: TelegramBot = this.bot,
        scope: CoroutineScope = this.scope,
        broadcastChannelsSize: Int = 100,
        onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
        upstreamUpdatesFlow: Flow<Update>? = null,
        triggersHolder: TriggersHolder = this.triggersHolder,
        onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler()
    ): BehaviourContextWithFSM<T> = copy(
        bot,
        scope,
        broadcastChannelsSize,
        onBufferOverflow,
        upstreamUpdatesFlow,
        triggersHolder
    )

    fun copy(
        bot: TelegramBot = this.bot,
        scope: CoroutineScope = this.scope,
        broadcastChannelsSize: Int = 100,
        onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
        upstreamUpdatesFlow: Flow<Update>? = null,
        triggersHolder: TriggersHolder = this.triggersHolder,
        onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
        updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>? = null
    ): BehaviourContextWithFSM<T> = copy(
        bot,
        scope,
        broadcastChannelsSize,
        onBufferOverflow,
        upstreamUpdatesFlow,
        triggersHolder,
        onStateHandlingErrorHandler
    )

    companion object {
        operator fun <T : State> invoke(
            behaviourContext: BehaviourContext,
            handlers: List<BehaviourWithFSMStateHandlerHolder<*, T>>,
            statesManager: StatesManager<T>,
            onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler()
        ) = DefaultBehaviourContextWithFSM<T>(behaviourContext, statesManager, handlers, onStateHandlingErrorHandler)
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
inline fun <reified I : O, O: State> BehaviourContextWithFSM<O>.onStateOrSubstate(handler: BehaviourWithFSMStateHandler<I, O>) = add(I::class, strict = false, handler)

/**
 * Add STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Strict means that
 * for input [State] will be used [State]::class == [kClass] and any [State] with exactly the same type will pass
 * requirements
 *
 * @see BehaviourWithFSMStateHandlerHolder
 * @see BehaviourContextWithFSM.addStrict
 */
@Suppress("MemberVisibilityCanBePrivate")
inline fun <reified I : O, O: State> BehaviourContextWithFSM<O>.strictlyOn(handler: BehaviourWithFSMStateHandler<I, O>) = addStrict(I::class, handler)

/**
 * Default realization of [BehaviourContextWithFSM]. It uses [behaviourContext] as a base for this object as
 * [BehaviourContext], but managing substates contexts updates for avoiding of updates lost between states
 * @param onStateHandlingErrorHandler Will be used in case if state handling has not been successfully completed in [launchStateHandling]
 */
class DefaultBehaviourContextWithFSM<T : State>(
    private val behaviourContext: BehaviourContext,
    private val statesManager: StatesManager<T>,
    private val handlers: List<BehaviourWithFSMStateHandlerHolder<*, T>>,
    private val onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler()
) : BehaviourContext by behaviourContext, BehaviourContextWithFSM<T> {
    private val updatesFlows = mutableMapOf<Any, DefaultBehaviourContextWithFSM<T>>()
    private val additionalHandlers = mutableListOf<BehaviourWithFSMStateHandlerHolder<*, T>>()
    private var actualHandlersList = additionalHandlers + handlers

    protected val statesJobs = mutableMapOf<T, Job>()
    protected val statesJobsMutex = Mutex()

    override suspend fun launchStateHandling(state: T, handlers: List<CheckableHandlerHolder<in T, T>>): T? {
        return launchStateHandling(state, handlers, onStateHandlingErrorHandler)
    }

    private fun getSubContext(context: Any) = updatesFlows.getOrPut(context) {
        createSubContext()
    }

    override suspend fun StatesMachine<in T>.handleState(state: T): T? {
        return getSubContext(
            state.context
        ).launchStateHandling(
            state,
            actualHandlersList
        )
    }

    override fun <I : T> add(kClass: KClass<I>, strict: Boolean, handler: BehaviourWithFSMStateHandler<I, T>) {
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
                    if (this@enableRemoveOnCompletion === statesJobs[state]) {
                        statesJobs.remove(state)
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
            updatesFlows.remove(it.context)
        }
        statesManager.onChainStateUpdated.subscribeSafelyWithoutExceptions(this) { (old, new) ->
            statesJobsMutex.withLock {
                runCatchingSafely { statesJobs.remove(old) ?.cancel() }
                runCatchingSafely { statesJobs.remove(new) ?.cancel() }
                statesJobs[new] = launch { statePerformer(new) }.apply { enableRemoveOnCompletion(new) }
            }
            if (old.context != new.context) {
                updatesFlows.remove(old.context)
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
        triggersHolder: TriggersHolder
    ): DefaultBehaviourContextWithFSM<T> = BehaviourContextWithFSM(
        behaviourContext.copy(bot, scope, broadcastChannelsSize, onBufferOverflow, upstreamUpdatesFlow, triggersHolder),
        handlers,
        statesManager,
        onStateHandlingErrorHandler
    )

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        triggersHolder: TriggersHolder,
        onStateHandlingErrorHandler: StateHandlingErrorHandler<T>
    ): DefaultBehaviourContextWithFSM<T> = BehaviourContextWithFSM(
        behaviourContext.copy(bot, scope, broadcastChannelsSize, onBufferOverflow, upstreamUpdatesFlow, triggersHolder),
        handlers,
        statesManager,
        onStateHandlingErrorHandler
    )
}
