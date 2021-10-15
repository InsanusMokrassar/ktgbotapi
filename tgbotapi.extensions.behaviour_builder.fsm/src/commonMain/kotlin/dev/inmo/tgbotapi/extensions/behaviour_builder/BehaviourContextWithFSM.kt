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

private suspend fun <I : State> BehaviourContextWithFSM.launchStateHandling(
    state: State,
    contextUpdatesFlow: Flow<Update>,
    handlers: List<BehaviourWithFSMStateHandlerHolder<out I>>
): State? {
    return handlers.firstOrNull { it.checkHandleable(state) } ?.run {
        handleState(contextUpdatesFlow, state)
    }
}

interface BehaviourContextWithFSM : BehaviourContext, StatesMachine {
    suspend fun start() = start(this)

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>?
    ): BehaviourContextWithFSM

    companion object {
        operator fun invoke(
            behaviourContext: BehaviourContext,
            handlers: List<BehaviourWithFSMStateHandlerHolder<*>>,
            statesManager: StatesManager
        ) = DefaultBehaviourContextWithFSM(behaviourContext, statesManager, handlers)
    }
}

class DefaultBehaviourContextWithFSM(
    private val behaviourContext: BehaviourContext,
    private val statesManager: StatesManager,
    private val handlers: List<BehaviourWithFSMStateHandlerHolder<*>>
) : BehaviourContext by behaviourContext, BehaviourContextWithFSM {
    private val updatesFlows = mutableMapOf<Any, Flow<Update>>()
    private fun getContextUpdatesFlow(context: Any) = updatesFlows.getOrPut(context) {
        allUpdatesFlow.accumulatorFlow(scope)
    }
    override suspend fun StatesMachine.handleState(state: State): State? = launchStateHandling(
        state,
        allUpdatesFlow,
        handlers
    )

    override fun start(scope: CoroutineScope): Job = scope.launchSafelyWithoutExceptions {
        val statePerformer: suspend (State) -> Unit = { state: State ->
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

    override suspend fun startChain(state: State) {
        statesManager.startChain(state)
    }

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>?
    ): BehaviourContextWithFSM = BehaviourContextWithFSM(
        behaviourContext.copy(bot, scope, broadcastChannelsSize, onBufferOverflow, upstreamUpdatesFlow, updatesFilter),
        handlers,
        statesManager
    )
}
