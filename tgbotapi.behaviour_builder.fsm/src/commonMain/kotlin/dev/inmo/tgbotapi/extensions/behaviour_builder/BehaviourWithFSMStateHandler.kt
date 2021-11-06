package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.fsm.common.*

fun interface BehaviourWithFSMStateHandler<I : O, O : State> {
    suspend fun BehaviourContextWithFSM<in O>.handleState(state: I): O?
}
