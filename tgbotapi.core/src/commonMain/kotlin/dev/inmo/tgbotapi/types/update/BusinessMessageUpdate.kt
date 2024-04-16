package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate

data class BusinessMessageUpdate(
    override val updateId: UpdateId,
    override val data: BusinessContentMessage<*>
) : BaseSentMessageUpdate {
    /**
     * @param newData Must be [BusinessContentMessage]
     */
    override fun copy(newData: ContentMessage<*>) = copy(updateId, newData as BusinessContentMessage<*>)
}
