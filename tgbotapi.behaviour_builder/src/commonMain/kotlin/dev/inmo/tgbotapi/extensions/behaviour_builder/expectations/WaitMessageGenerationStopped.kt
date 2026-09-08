package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.messageGenerationStoppedUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.MessageGenerationStopped
import kotlinx.coroutines.flow.Flow

fun BehaviourContext.waitMessageGenerationStopped(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<MessageGenerationStopped> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.messageGenerationStoppedUpdateOrNull() ?.data).let(::listOfNotNull)
}
