package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.chatBoostUpdatedUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.boosts.ChatBoostUpdated
import kotlinx.coroutines.flow.Flow

suspend fun BehaviourContext.waitChatBoostUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<ChatBoostUpdated> = expectFlow(
    initRequest,
    errorFactory,
) {
    it.chatBoostUpdatedUpdateOrNull() ?.data.let(::listOfNotNull)
}
