package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.botSubscriptionUpdatedUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.payments.BotSubscriptionUpdated
import kotlinx.coroutines.flow.Flow

fun BehaviourContext.waitBotSubscriptionUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<BotSubscriptionUpdated> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.botSubscriptionUpdatedUpdateOrNull() ?.data).let(::listOfNotNull)
}
