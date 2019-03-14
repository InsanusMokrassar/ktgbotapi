package com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*

data class EditChannelPostMediaGroupUpdate(
    override val updateId: UpdateIdentifier,
    override val data: MediaGroupMessage
) : MediaGroupUpdate {
    constructor(sourceUpdate: EditChannelPostUpdate) : this(
        sourceUpdate.updateId,
        sourceUpdate.data as MediaGroupMessage
    )
}