package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.polls.PollAnswer
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class PollAnswerUpdate(
    override val updateId: UpdateId,
    override val data: PollAnswer,
) : Update
