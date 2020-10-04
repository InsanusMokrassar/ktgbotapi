package dev.inmo.tgbotapi.types.update.MediaGroupUpdates

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.update.EditMessageUpdate

data class EditMessageMediaGroupUpdate(
    override val origin: EditMessageUpdate
) : EditMediaGroupUpdate {
    override val updateId: UpdateIdentifier = origin.updateId
    override val data: MediaGroupMessage = origin.data as MediaGroupMessage
}
