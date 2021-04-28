package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.media.PhotoContent
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

fun SendPhoto(
    chatId: ChatIdentifier,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<PhotoContent>> {
    val data = SendPhotoData(
        chatId,
        (photo as? FileId) ?.fileId,
        text,
        parseMode,
        null,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
    return data.photo ?.let {
        data
    } ?:  MultipartRequestImpl(
        data,
        SendPhotoFiles(photo as MultipartFile)
    )
}

fun SendPhoto(
    chatId: ChatIdentifier,
    photo: InputFile,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<PhotoContent>> {
    val data = SendPhotoData(
        chatId,
        (photo as? FileId) ?.fileId,
        entities.makeString(),
        null,
        entities.toRawMessageEntities(),
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
    return data.photo ?.let {
        data
    } ?:  MultipartRequestImpl(
        data,
        SendPhotoFiles(photo as MultipartFile)
    )
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<PhotoContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendPhotoData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(photoField)
    val photo: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<PhotoContent>>,
    SendMessageRequest<ContentMessage<PhotoContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<PhotoContent>>,
    TextableSendMessageRequest<ContentMessage<PhotoContent>>
{
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    init {
        text ?.let {
            if (it.length !in captionLength) {
                throwRangeError("Caption length", captionLength, it.length)
            }
        }
    }

    override fun method(): String = "sendPhoto"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<PhotoContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendPhotoFiles internal constructor(
    val photo: MultipartFile
) : Files by mapOf(
    photoField to photo
)
