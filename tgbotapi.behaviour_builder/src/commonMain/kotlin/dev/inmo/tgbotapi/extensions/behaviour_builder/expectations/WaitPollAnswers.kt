package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asPollAnswerUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.polls.PollAnswer
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

typealias PollAnswerMapper = suspend PollAnswer.() -> PollAnswer?

suspend fun BehaviourContext.waitPollAnswers(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<PollAnswer> = expectFlow(
    initRequest,
    errorFactory
) {
    it.asPollAnswerUpdate() ?.data.let(::listOfNotNull)
}
