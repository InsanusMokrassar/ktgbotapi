package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.inlineQueryUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.InlineQueries.query.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

typealias InlineQueryMapper<T> = suspend T.() -> T?

@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified O : InlineQuery> BehaviourContext.waitInlineQueries(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.inlineQueryUpdateOrNull() ?.data as? O).let(::listOfNotNull)
}

fun BehaviourContext.waitAnyInlineQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitInlineQueries<InlineQuery>(initRequest, errorFactory)

fun BehaviourContext.waitBaseInlineQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitInlineQueries<BaseInlineQuery>(initRequest, errorFactory)
fun BehaviourContext.waitLocationInlineQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitInlineQueries<LocationInlineQuery>(initRequest, errorFactory)
