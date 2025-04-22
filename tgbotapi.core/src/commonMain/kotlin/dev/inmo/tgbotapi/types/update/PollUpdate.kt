package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.update.abstracts.Update

data class PollUpdate(
    override val updateId: UpdateId,
    override val data: Poll,
) : Update
