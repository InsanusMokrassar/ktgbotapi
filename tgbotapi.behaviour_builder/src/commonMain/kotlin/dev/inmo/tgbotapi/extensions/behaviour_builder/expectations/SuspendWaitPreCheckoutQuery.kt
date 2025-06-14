package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.preCheckoutQueryUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import kotlinx.coroutines.flow.Flow

suspend fun BehaviourContext.waitPreCheckoutQueries(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<PreCheckoutQuery> = expectFlow(
    initRequest,
    errorFactory
) {
    it.preCheckoutQueryUpdateOrNull() ?.data.let(::listOfNotNull)
}
