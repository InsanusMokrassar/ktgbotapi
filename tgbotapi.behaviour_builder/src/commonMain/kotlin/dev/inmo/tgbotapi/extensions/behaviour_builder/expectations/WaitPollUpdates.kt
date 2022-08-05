package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.pollUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

typealias PollMapper<T> = suspend T.() -> T?

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : Poll> BehaviourContext.waitPolls(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.pollUpdateOrNull() ?.data as? O).let(::listOfNotNull)
}

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitPollUpdates(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitPolls<Poll>(initRequest, errorFactory)

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitQuizPollUpdates(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitPolls<QuizPoll>(initRequest, errorFactory)

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitRegularPollUpdates(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitPolls<RegularPoll>(initRequest, errorFactory)
