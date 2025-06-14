package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.chosenInlineResultUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O> BehaviourContext.waitChosenInlineResults(
    initRequest: Request<*>,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.chosenInlineResultUpdateOrNull() ?.data as? O).let(::listOfNotNull)
}

suspend fun BehaviourContext.waitChosenInlineResult(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChosenInlineResults<ChosenInlineResult>(initRequest, errorFactory)

suspend fun BehaviourContext.waitLocationChosenInlineResult(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChosenInlineResults<LocationChosenInlineResult>(initRequest, errorFactory)

suspend fun BehaviourContext.waitBaseChosenInlineResult(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChosenInlineResults<BaseChosenInlineResult>(initRequest, errorFactory)
