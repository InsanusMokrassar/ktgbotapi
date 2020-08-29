package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.Poll
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

data class PollUpdate(
    override val updateId: UpdateIdentifier,
    override val data: Poll
) : Update
