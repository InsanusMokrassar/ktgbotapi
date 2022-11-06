package dev.inmo.tgbotapi.types.update.media_group

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.update.EditChannelPostUpdate

data class EditChannelPostMediaGroupUpdate(
    override val origin: EditChannelPostUpdate
) : EditMediaGroupUpdate {
    override val updateId: UpdateIdentifier = origin.updateId
    override val data: MediaGroupMessage<MediaGroupPartContent> = origin.data as MediaGroupMessage<MediaGroupPartContent>
}
