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
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(receiverUserIdField)
    @EncodeDefault
    override val receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(ephemeralMessageIdField)
    @EncodeDefault
    override val ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditEphemeralMessage, EditReplyMessage {

    override fun method(): String = editEphemeralMessageReplyMarkupMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
