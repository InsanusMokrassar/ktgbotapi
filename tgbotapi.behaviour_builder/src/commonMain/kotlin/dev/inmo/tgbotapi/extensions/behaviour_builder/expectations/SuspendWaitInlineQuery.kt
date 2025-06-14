package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.inlineQueryUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.InlineQueries.query.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : InlineQuery> BehaviourContext.waitInlineQueries(
    initRequest: Request<*>,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.inlineQueryUpdateOrNull() ?.data as? O).let(::listOfNotNull)
}

suspend fun BehaviourContext.waitAnyInlineQuery(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitInlineQueries<InlineQuery>(initRequest, errorFactory)

suspend fun BehaviourContext.waitBaseInlineQuery(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitInlineQueries<BaseInlineQuery>(initRequest, errorFactory)
suspend fun BehaviourContext.waitLocationInlineQuery(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitInlineQueries<LocationInlineQuery>(initRequest, errorFactory)
