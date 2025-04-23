package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.chatMessageReactionsCountUpdatedUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionsCountUpdated
import kotlinx.coroutines.flow.Flow

suspend inline fun BehaviourContext.waitChatMessageReactionsCountUpdated(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
): Flow<ChatMessageReactionsCountUpdated> = expectFlow(
    initRequest,
    errorFactory,
) {
    (it.chatMessageReactionsCountUpdatedUpdateOrNull() ?.data).let(::listOfNotNull)
}
