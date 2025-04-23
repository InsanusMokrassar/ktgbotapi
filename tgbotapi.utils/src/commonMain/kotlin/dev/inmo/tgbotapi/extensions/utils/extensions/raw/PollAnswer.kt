package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.types.PollId
import dev.inmo.tgbotapi.types.polls.PollAnswer
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature(RawFieldsUsageWarning)
val PollAnswer.poll_id: PollId
    get() = pollId

@RiskFeature(RawFieldsUsageWarning)
val PollAnswer.option_ids: List<Int>
    get() = chosen
