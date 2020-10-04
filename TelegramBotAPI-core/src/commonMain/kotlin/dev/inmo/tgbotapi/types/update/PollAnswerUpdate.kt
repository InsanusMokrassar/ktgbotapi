package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.polls.PollAnswer
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class PollAnswerUpdate(
    override val updateId: UpdateIdentifier,
    override val data: PollAnswer
) : Update
