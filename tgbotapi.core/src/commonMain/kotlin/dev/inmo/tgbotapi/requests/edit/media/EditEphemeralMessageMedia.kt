package dev.inmo.tgbotapi.requests.edit.media

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.MultipartRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.CoveredTelegramMedia
import dev.inmo.tgbotapi.types.media.PhotoedTelegramMedia
import dev.inmo.tgbotapi.types.media.TelegramFreeMedia
import dev.inmo.tgbotapi.types.media.ThumbedTelegramMedia
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

const val editEphemeralMessageMediaMethod = "editEphemeralMessageMedia"

@Serializable
data class EditEphemeralMessageMedia(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(receiverUserIdField)
    @EncodeDefault
    override val receiverUserId: UserId,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(ephemeralMessageIdField)
    @EncodeDefault
    override val ephemeralMessageId: EphemeralMessageId,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(mediaField)
    override val media: TelegramFreeMedia,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditEphemeralMessage, EditReplyMessage, EditMediaMessage, MultipartRequest.Common<Unit> {
    constructor(
        chatId: EphemeralChatId,
        media: TelegramFreeMedia,
        replyMarkup: InlineKeyboardMarkup? = null
    ): this(
        chatId,
        chatId.receiverUser,
        requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
        media,
        replyMarkup
    )

    override val data: SimpleRequest<Unit>
        get() = this
    override val mediaMap: Map<String, MultipartFile> by lazy {
        listOfNotNull(
            media.file as? MultipartFile,
            (media as? PhotoedTelegramMedia) ?.photo as? MultipartFile,
            (media as? ThumbedTelegramMedia) ?.thumb as? MultipartFile,
            (media as? CoveredTelegramMedia) ?.cover as? MultipartFile
        ).associateBy { it.fileId }
    }

    override fun method(): String = editEphemeralMessageMediaMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
