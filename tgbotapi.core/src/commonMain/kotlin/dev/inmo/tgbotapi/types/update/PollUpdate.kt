package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class PollUpdate(
    override val updateId: UpdateIdentifier,
    override val data: Poll
) : Update
