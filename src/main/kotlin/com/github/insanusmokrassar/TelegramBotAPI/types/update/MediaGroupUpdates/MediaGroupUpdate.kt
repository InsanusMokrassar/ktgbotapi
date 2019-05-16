package com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

interface MediaGroupUpdate : Update

interface SentMediaGroupUpdate: MediaGroupUpdate {
    override val data: List<MediaGroupMessage>
    val origins: List<BaseMessageUpdate>
}

interface EditMediaGroupUpdate : MediaGroupUpdate {
    override val data: MediaGroupMessage
    val origin: BaseMessageUpdate
}
