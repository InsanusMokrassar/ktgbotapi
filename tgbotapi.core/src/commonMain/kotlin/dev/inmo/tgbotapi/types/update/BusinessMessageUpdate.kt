package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate

data class BusinessMessageUpdate(
    override val updateId: UpdateId,
    override val data: BusinessContentMessage<*>
) : BaseSentMessageUpdate {
    override fun copy(newData: BusinessContentMessage<*>) = copy(updateId, newData)
}
