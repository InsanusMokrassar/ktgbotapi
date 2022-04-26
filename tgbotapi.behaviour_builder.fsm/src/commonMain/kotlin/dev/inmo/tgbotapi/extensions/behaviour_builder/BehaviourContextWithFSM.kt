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

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>?
    ): BehaviourContextWithFSM<T>

    companion object {
        operator fun <T : State> invoke(
            behaviourContext: BehaviourContext,
            handlers: List<BehaviourWithFSMStateHandlerHolder<*, T>>,
            statesManager: StatesManager<T>
        ) = DefaultBehaviourContextWithFSM<T>(behaviourContext, statesManager, handlers)
    }
}

/**
 * Default realization of [BehaviourContextWithFSM]. It uses [behaviourContext] as a base for this object as
 * [BehaviourContext], but managing substates contexts updates for avoiding of updates lost between states
 */
class DefaultBehaviourContextWithFSM<T : State>(
    private val behaviourContext: BehaviourContext,
    private val statesManager: StatesManager<T>,
    private val handlers: List<BehaviourWithFSMStateHandlerHolder<*, T>>
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
    ): BehaviourContextWithFSM<T> = BehaviourContextWithFSM(
        behaviourContext.copy(bot, scope, broadcastChannelsSize, onBufferOverflow, upstreamUpdatesFlow, updatesFilter),
        handlers,
        statesManager
    )
}
