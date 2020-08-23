package com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.EditMessageUpdate

data class EditMessageMediaGroupUpdate(
    override val origin: EditMessageUpdate
) : EditMediaGroupUpdate {
    override val updateId: UpdateIdentifier = origin.updateId
    override val data: MediaGroupMessage = origin.data as MediaGroupMessage
}
