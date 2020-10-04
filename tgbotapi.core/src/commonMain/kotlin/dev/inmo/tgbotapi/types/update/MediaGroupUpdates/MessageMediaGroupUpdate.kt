package dev.inmo.tgbotapi.types.update.MediaGroupUpdates

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate

data class MessageMediaGroupUpdate(
    override val origins: List<BaseMessageUpdate>
) : SentMediaGroupUpdate {
    override val updateId: UpdateIdentifier = origins.last().updateId
    override val data: List<MediaGroupMessage> = origins.mapNotNull { it.data as? MediaGroupMessage }
}