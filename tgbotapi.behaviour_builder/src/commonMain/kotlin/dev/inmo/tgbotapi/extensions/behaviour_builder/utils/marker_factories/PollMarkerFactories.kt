package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.polls.PollAnswer

object ByIdPollMarkerFactory : MarkerFactory<Poll, Any> {
    override suspend fun invoke(data: Poll) = data.id
}

object ByIdPollAnswerMarkerFactory : MarkerFactory<PollAnswer, Any> {
    override suspend fun invoke(data: PollAnswer) = data.pollId
}
