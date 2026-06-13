package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.guestMessageUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.RequestGuestContentMessage
import kotlinx.coroutines.flow.Flow

fun BehaviourContext.waitGuestRequestMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<RequestGuestContentMessage<*>> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.guestMessageUpdateOrNull() ?.data).let(::listOfNotNull)
}
