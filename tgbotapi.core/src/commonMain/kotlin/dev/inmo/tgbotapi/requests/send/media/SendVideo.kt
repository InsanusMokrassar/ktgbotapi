package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.MessageEntity.RawMessageEntity
import dev.inmo.tgbotapi.types.MessageEntity.toRawMessageEntities
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.media.VideoContent
import dev.inmo.tgbotapi.utils.mapOfNotNull
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

fun SendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    caption: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VideoContent>> {
    val videoAsFileId = (video as? FileId) ?.fileId
    val videoAsFile = video as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendVideoData(
        chatId,
        videoAsFileId,
        thumbAsFileId,
        caption,
        parseMode,
        null,
        duration,
        width,
        height,
        supportStreaming,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    return if (videoAsFile == null && thumbAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendVideoFiles(videoAsFile, thumbAsFile)
        )
    }
}

fun SendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    entities: List<TextSource>,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VideoContent>> {
    val videoAsFileId = (video as? FileId) ?.fileId
    val videoAsFile = video as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendVideoData(
        chatId,
        videoAsFileId,
        thumbAsFileId,
        entities.makeString(),
        null,
        entities.toRawMessageEntities(),
        duration,
        width,
        height,
        supportStreaming,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    return if (videoAsFile == null && thumbAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendVideoFiles(videoAsFile, thumbAsFile)
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<VideoContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendVideoData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(videoField)
    val video: String? = null,
    @SerialName(thumbField)
    override val thumb: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(widthField)
    override val width: Int? = null,
    @SerialName(heightField)
    override val height: Int? = null,
    @SerialName(supportStreamingField)
    val supportStreaming: Boolean? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<VideoContent>>,
    SendMessageRequest<ContentMessage<VideoContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<VideoContent>>,
    TextableSendMessageRequest<ContentMessage<VideoContent>>,
    ThumbedSendMessageRequest<ContentMessage<VideoContent>>,
    DuratedSendMessageRequest<ContentMessage<VideoContent>>,
    SizedSendMessageRequest<ContentMessage<VideoContent>>
{
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextParts(text ?: return@lazy null) ?.justTextSources()
    }

    init {
        text ?.let {
            if (it.length !in captionLength) {
                throwRangeError("Caption length", captionLength, it.length)
            }
        }
    }

    override fun method(): String = "sendVideo"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<VideoContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendVideoFiles internal constructor(
    val video: MultipartFile? = null,
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    videoField to video,
    thumbField to thumb
)
