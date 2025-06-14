package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.chatBoostRemovedUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.boosts.ChatBoostRemoved
import kotlinx.coroutines.flow.Flow

suspend fun BehaviourContext.waitChatBoostRemoved(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ChatBoostRemoved> = expectFlow(
    initRequest,
    errorFactory
) {
    it.chatBoostRemovedUpdateOrNull() ?.data.let(::listOfNotNull)
}
