@file:Suppress("FunctionName")

package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.abstracts.WithCustomStartMediaData
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
import dev.inmo.tgbotapi.types.message.content.VideoContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.mapOfNotNull
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

fun SendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumbnail: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId?,// = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VideoContent>> {
    val videoAsFile = video as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile
    val coverAsFile = cover as? MultipartFile

    val data = SendVideoData(
        chatId = chatId,
        video = video,
        thumbnail = thumbnail ?.fileId,
        cover = cover,
        startTimestamp = startTimestamp,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
        duration = duration,
        width = width,
        height = height,
        supportStreaming = supportStreaming,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    return if (videoAsFile == null && thumbAsFile == null && coverAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(videoAsFile, thumbAsFile, coverAsFile).associateBy { it.fileId }
        )
    }
}

fun SendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumbnail: InputFile? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId?,// = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VideoContent>> {
    val videoAsFile = video as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile
    val coverAsFile = cover as? MultipartFile

    val data = SendVideoData(
        chatId = chatId,
        video = video,
        thumbnail = thumbnail ?.fileId,
        cover = cover,
        startTimestamp = startTimestamp,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
        duration = duration,
        width = width,
        height = height,
        supportStreaming = supportStreaming,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    return if (videoAsFile == null && thumbAsFile == null && coverAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(videoAsFile, thumbAsFile, coverAsFile).associateBy { it.fileId }
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<VideoContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@ConsistentCopyVisibility
@Serializable
data class SendVideoData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(videoField)
    val video: InputFile,
    @SerialName(thumbnailField)
    override val thumbnail: String? = null,
    @SerialName(coverField)
    override val cover: InputFile? = null,
    @SerialName(startTimestampField)
    override val startTimestamp: Seconds? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(showCaptionAboveMediaField)
    override val showCaptionAboveMedia: Boolean = false,
    @SerialName(hasSpoilerField)
    override val spoilered: Boolean = false,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(widthField)
    override val width: Int? = null,
    @SerialName(heightField)
    override val height: Int? = null,
    @SerialName(supportStreamingField)
    val supportStreaming: Boolean? = null,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(messageThreadIdField)
    @EncodeDefault
    override val threadId: MessageThreadId? = chatId.threadId,
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault
    @SerialName(directMessagesTopicIdField)
    override val directMessageThreadId: DirectMessageThreadId?,// = chatId.directMessageThreadId
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(allowPaidBroadcastField)
    override val allowPaidBroadcast: Boolean = false,
    @SerialName(messageEffectIdField)
    override val effectId: EffectId? = null,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<VideoContent>>,
    SendContentMessageRequest<ContentMessage<VideoContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<VideoContent>>,
    TextableSendMessageRequest<ContentMessage<VideoContent>>,
    ThumbedSendMessageRequest<ContentMessage<VideoContent>>,
    DuratedSendMessageRequest<ContentMessage<VideoContent>>,
    SizedSendMessageRequest<ContentMessage<VideoContent>>,
    WithCustomizableCaptionRequest<ContentMessage<VideoContent>>,
    CoveredSendMessageRequest<ContentMessage<VideoContent>>,
    WithCustomStartMediaData,
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

    override fun method(): String = "sendVideo"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<VideoContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Suppress("unused")
@ConsistentCopyVisibility
data class SendVideoFiles internal constructor(
    val video: MultipartFile? = null,
    val thumbnail: MultipartFile? = null
) : Files by mapOfNotNull(
    videoField to video,
    thumbnailField to thumbnail
)
