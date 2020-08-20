package com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate

data class MessageMediaGroupUpdate(
    override val origins: List<BaseMessageUpdate>
) : SentMediaGroupUpdate {
    override val updateId: UpdateIdentifier = origins.last().updateId
    override val data: List<MediaGroupMessage> = origins.mapNotNull { it.data as? MediaGroupMessage }
}