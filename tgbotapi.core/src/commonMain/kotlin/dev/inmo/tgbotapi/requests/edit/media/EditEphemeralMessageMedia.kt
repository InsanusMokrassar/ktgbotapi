package dev.inmo.tgbotapi.requests.edit.media

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.TelegramFreeMedia
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

const val editEphemeralMessageMediaMethod = "editEphemeralMessageMedia"

@Serializable
data class EditEphemeralMessageMedia(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(receiverUserIdField)
    override val receiverUserId: UserId,
    @SerialName(ephemeralMessageIdField)
    override val ephemeralMessageId: EphemeralMessageId,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(mediaField)
    override val media: TelegramFreeMedia,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditEphemeralMessage, EditReplyMessage, EditMediaMessage {

    init {
        require(media.file !is MultipartFile) {
            "For editing of ephemeral media messages you MUST use file id (uploading of a new file is not supported)"
        }
    }

    override fun method(): String = editEphemeralMessageMediaMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
