package dev.inmo.tgbotapi.requests.edit.reply_markup

import dev.inmo.tgbotapi.requests.edit.abstracts.EditEphemeralMessage
import dev.inmo.tgbotapi.requests.edit.abstracts.EditReplyMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

const val editEphemeralMessageReplyMarkupMethod = "editEphemeralMessageReplyMarkup"

@Serializable
data class EditEphemeralMessageReplyMarkup(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(receiverUserIdField)
    override val receiverUserId: UserId,
    @SerialName(ephemeralMessageIdField)
    override val ephemeralMessageId: EphemeralMessageId,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditEphemeralMessage, EditReplyMessage {

    override fun method(): String = editEphemeralMessageReplyMarkupMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
