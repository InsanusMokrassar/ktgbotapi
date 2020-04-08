package com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.*

interface MediaGroupUpdate : Update

interface SentMediaGroupUpdate: MediaGroupUpdate {
    override val data: List<MediaGroupMessage>
    val origins: List<BaseMessageUpdate>
}

interface EditMediaGroupUpdate : BaseEditMessageUpdate, MediaGroupUpdate {
    override val data: MediaGroupMessage
    val origin: BaseMessageUpdate
}
