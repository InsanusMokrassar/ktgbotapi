package dev.inmo.tgbotapi.types.update.media_group

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate

data class ChannelPostMediaGroupUpdate(
    override val origins: List<BaseMessageUpdate>
) : SentMediaGroupUpdate {
    override val updateId: UpdateIdentifier = origins.last().updateId
    override val data: List<MediaGroupMessage<MediaGroupContent>> = origins.mapNotNull { it.data as? MediaGroupMessage<MediaGroupContent> }
}
