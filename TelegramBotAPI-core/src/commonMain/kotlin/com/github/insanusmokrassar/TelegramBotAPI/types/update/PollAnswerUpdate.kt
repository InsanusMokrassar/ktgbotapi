package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.PollAnswer
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

data class PollAnswerUpdate(
    override val updateId: UpdateIdentifier,
    override val data: PollAnswer
) : Update
