package com.github.insanusmokrassar.TelegramBotAPI.types.update

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.CommonMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseEditMessageUpdate

data class EditChannelPostUpdate(
    override val updateId: UpdateIdentifier,
    override val data: CommonMessage<*>
) : BaseEditMessageUpdate
