package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.launchSafelyWithoutExceptions
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.micro_utils.fsm.common.*
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.micro_utils.coroutines.accumulatorFlow
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlin.jvm.JvmName
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

    suspend fun launchStateHandling(
        state: T,
        contextUpdatesFlow: Flow<Update>,
        handlers: List<BehaviourWithFSMStateHandlerHolder<*, T>>
    ): T? {
        return handlers.firstOrNull { it.checkHandleable(state) } ?.run {
            handleState(contextUpdatesFlow, state)
        }
    }

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
        updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>?
    ): BehaviourContextWithFSM<T>

    companion object {
        @JvmName("invokeWithMutableList")
        operator fun <T : State> invoke(
            behaviourContext: BehaviourContext,
            handlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>>,
            statesManager: StatesManager<T>
        ) = DefaultBehaviourContextWithFSM<T>(behaviourContext, statesManager, handlers.toMutableList())
        operator fun <T : State> invoke(
            behaviourContext: BehaviourContext,
            handlers: List<BehaviourWithFSMStateHandlerHolder<*, T>>,
            statesManager: StatesManager<T>
        ) = invoke<T>(behaviourContext, handlers.toMutableList(), statesManager)
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
 */
class DefaultBehaviourContextWithFSM<T : State>(
    private val behaviourContext: BehaviourContext,
    private val statesManager: StatesManager<T>,
    private val handlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>>
) : BehaviourContext by behaviourContext, BehaviourContextWithFSM<T> {
    private val updatesFlows = mutableMapOf<Any, Flow<Update>>()
    private fun getContextUpdatesFlow(context: Any) = updatesFlows.getOrPut(context) {
        allUpdatesFlow.accumulatorFlow(scope)
    }

    override suspend fun StatesMachine<in T>.handleState(state: T): T? = launchStateHandling(
        state,
        allUpdatesFlow,
        handlers
    )

    override fun <I : T> add(kClass: KClass<I>, strict: Boolean, handler: BehaviourWithFSMStateHandler<I, T>) {
        handlers.add(BehaviourWithFSMStateHandlerHolder(kClass, strict, handler))
    }

    override fun start(scope: CoroutineScope): Job = scope.launchSafelyWithoutExceptions {
        val statePerformer: suspend (T) -> Unit = { state: T ->
            val newState = launchStateHandling(state, getContextUpdatesFlow(state.context), handlers)
            if (newState != null) {
                statesManager.update(state, newState)
            } else {
                statesManager.endChain(state)
            }
        }
        statesManager.onStartChain.subscribeSafelyWithoutExceptions(this) {
            launch { statePerformer(it) }
        }
        statesManager.onChainStateUpdated.subscribeSafelyWithoutExceptions(this) { (old, new) ->
            if (old.context != new.context) {
                updatesFlows.remove(old.context)
            }
            launch { statePerformer(new) }
        }
        statesManager.onEndChain.subscribeSafelyWithoutExceptions(this) {
            updatesFlows.remove(it.context)
        }

        statesManager.getActiveStates().forEach {
            launch { statePerformer(it) }
        }
    }

    override suspend fun startChain(state: T) {
        statesManager.startChain(state)
    }

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>?
    ): DefaultBehaviourContextWithFSM<T> = BehaviourContextWithFSM(
        behaviourContext.copy(bot, scope, broadcastChannelsSize, onBufferOverflow, upstreamUpdatesFlow, updatesFilter),
        handlers.toMutableList(),
        statesManager
    )
}
