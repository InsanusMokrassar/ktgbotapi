package com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.ChannelPostUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MessageUpdate

data class ChannelPostMediaGroupUpdate(
    override val updateId: UpdateIdentifier,
    override val data: MediaGroupMessage
) : MediaGroupUpdate {
    constructor(sourceUpdate: ChannelPostUpdate) : this(
        sourceUpdate.updateId,
        sourceUpdate.data as MediaGroupMessage
    )
}