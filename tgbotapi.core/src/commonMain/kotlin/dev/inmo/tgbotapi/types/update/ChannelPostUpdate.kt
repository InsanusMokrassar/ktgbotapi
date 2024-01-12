package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate

data class ChannelPostUpdate(
    override val updateId: UpdateIdentifier,
    override val data: AccessibleMessage
) : BaseSentMessageUpdate {
    override fun copy(newData: AccessibleMessage): BaseSentMessageUpdate = copy(updateId, newData)
}
