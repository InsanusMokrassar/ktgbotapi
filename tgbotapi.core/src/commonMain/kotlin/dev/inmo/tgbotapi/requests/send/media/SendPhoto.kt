package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.PhotoContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

fun SendPhoto(
    chatId: ChatIdentifier,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<PhotoContent>> {
    val data = SendPhotoData(
        chatId,
        photo,
        text,
        parseMode,
        null,
        spoilered,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
    return if (photo is MultipartFile) {
        CommonMultipartFileRequest(
            data,
            listOf(photo).associateBy { it.fileId }
        )
    } else {
        data
    }
}

fun SendPhoto(
    chatId: ChatIdentifier,
    photo: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<PhotoContent>> {
    val data = SendPhotoData(
        chatId,
        photo,
        entities.makeString(),
        null,
        entities.toRawMessageEntities(),
        spoilered,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )

    return if (photo is MultipartFile) {
        CommonMultipartFileRequest(
            data,
            listOf(photo).associateBy { it.fileId }
        )
    } else {
        data
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<PhotoContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendPhotoData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(photoField)
    val photo: InputFile,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(hasSpoilerField)
    override val spoilered: Boolean = false,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<PhotoContent>>,
    SendMessageRequest<ContentMessage<PhotoContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<PhotoContent>>,
    TextableSendMessageRequest<ContentMessage<PhotoContent>>,
    OptionallyWithSpoilerRequest
{
    override val textSources: TextSourcesList? by lazy {
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
