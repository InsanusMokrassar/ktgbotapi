package dev.inmo.tgbotapi.requests.edit.media

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.MultipartRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.media.TelegramFreeMedia
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.MediaContent
import kotlinx.serialization.*

const val editMessageMediaMethod = "editMessageMedia"

internal val MediaContentMessageResultDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<ContentMessage<MediaContent>>()

@Serializable
data class EditChatMessageMedia(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(mediaField)
    override val media: TelegramFreeMedia,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditChatMessage<MediaContent>, EditReplyMessage, EditMediaMessage, MultipartRequest.Common<ContentMessage<MediaContent>> {
    override val data: SimpleRequest<ContentMessage<MediaContent>>
        get() = this
    override val mediaMap: Map<String, MultipartFile> by lazy {
        (media.file as? MultipartFile) ?.let {
            mapOf(it.fileId to it)
        } ?: emptyMap()
    }

//    init {
//        if (media.file is MultipartFile) {
//            throw IllegalArgumentException("For editing of media messages you MUST use file id (according to documentation)")
//        }
//    }

    override fun method(): String = editMessageMediaMethod
    override val resultDeserializer: DeserializationStrategy<ContentMessage<MediaContent>>
        get() = MediaContentMessageResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
