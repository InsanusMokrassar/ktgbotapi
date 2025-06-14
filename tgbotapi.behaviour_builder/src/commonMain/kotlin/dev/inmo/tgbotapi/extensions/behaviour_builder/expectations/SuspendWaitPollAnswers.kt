package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.pollAnswerUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.polls.PollAnswer
import kotlinx.coroutines.flow.Flow

suspend fun BehaviourContext.waitPollAnswers(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<PollAnswer> = expectFlow(
    initRequest,
    errorFactory
) {
    it.pollAnswerUpdateOrNull() ?.data.let(::listOfNotNull)
}
