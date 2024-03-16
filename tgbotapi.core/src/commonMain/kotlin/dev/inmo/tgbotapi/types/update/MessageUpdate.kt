package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate

data class MessageUpdate(
    override val updateId: UpdateId,
    override val data: AccessibleMessage
) : BaseSentMessageUpdate {
    override fun copy(newData: AccessibleMessage) = copy(updateId, newData)
}
