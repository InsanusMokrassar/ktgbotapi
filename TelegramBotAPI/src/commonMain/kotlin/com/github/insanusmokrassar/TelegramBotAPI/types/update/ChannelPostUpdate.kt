package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseSentMessageUpdate

data class ChannelPostUpdate(
    override val updateId: UpdateIdentifier,
    override val data: Message
) : BaseSentMessageUpdate
