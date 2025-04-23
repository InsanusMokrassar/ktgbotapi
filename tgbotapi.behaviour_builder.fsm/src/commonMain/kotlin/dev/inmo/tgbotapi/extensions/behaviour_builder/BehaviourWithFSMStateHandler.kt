package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.fsm.common.*

fun interface BehaviourWithFSMStateHandler<I : O, O : State> : StatesHandler<I, O> {
    suspend fun BehaviourContextWithFSM<in O>.handleState(state: I): O?

    override suspend fun StatesMachine<in O>.handleState(state: I): O? = if (this is BehaviourContextWithFSM) {
        handleState(state)
    } else {
        null
    }
}
