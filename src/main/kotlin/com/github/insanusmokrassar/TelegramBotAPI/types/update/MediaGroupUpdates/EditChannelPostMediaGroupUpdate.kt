package com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.EditChannelPostUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate

data class EditChannelPostMediaGroupUpdate(
    override val origin: EditChannelPostUpdate
) : EditMediaGroupUpdate {
    override val updateId: UpdateIdentifier = origin.updateId
    override val data: MediaGroupMessage = origin.data as MediaGroupMessage
}