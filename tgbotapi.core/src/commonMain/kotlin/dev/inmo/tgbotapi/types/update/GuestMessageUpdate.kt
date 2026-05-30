package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.RequestGuestContentMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate

data class GuestMessageUpdate(
    override val updateId: UpdateId,
    override val data: RequestGuestContentMessage<*>
) : BaseSentMessageUpdate {
    /**
     * @param newData Must be [GuestContentMessage]
     */
    override fun copy(newData: AccessibleMessage) = copy(updateId, newData as RequestGuestContentMessage<*>)
}
