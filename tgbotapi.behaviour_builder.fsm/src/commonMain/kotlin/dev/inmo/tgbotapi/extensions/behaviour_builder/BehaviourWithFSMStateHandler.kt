package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.fsm.common.*

fun interface BehaviourWithFSMStateHandler<T : State> {
    suspend fun BehaviourContextWithFSM.handleState(state: T): State?
}
