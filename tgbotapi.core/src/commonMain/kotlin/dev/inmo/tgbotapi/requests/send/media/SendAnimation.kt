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
import dev.inmo.tgbotapi.types.message.content.AnimationContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.mapOfNotNull
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

fun SendAnimation(
    chatId: ChatIdentifier,
    animation: InputFile,
    thumbnail: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<AnimationContent>> {
    val animationAsFile = animation as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile

    val data = SendAnimationData(
        chatId,
        animation,
        thumbnail ?.fileId,
        text,
        parseMode,
        null,
        spoilered,
        duration,
        width,
        height,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )

    return if (animationAsFile == null && thumbAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(animationAsFile, thumbAsFile).associateBy { it.fileId }
        )
    }
}

fun SendAnimation(
    chatId: ChatIdentifier,
    animation: InputFile,
    thumbnail: InputFile? = null,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<AnimationContent>> {
    val animationAsFile = animation as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile

    val data = SendAnimationData(
        chatId,
        animation,
        thumbnail ?.fileId,
        entities.makeString(),
        null,
        entities.toRawMessageEntities(),
        spoilered,
        duration,
        width,
        height,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )

    return if (animationAsFile == null && thumbAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(animationAsFile, thumbAsFile).associateBy { it.fileId }
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<AnimationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendAnimationData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(animationField)
    val animation: InputFile,
    @SerialName(thumbnailField)
    override val thumbnail: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(hasSpoilerField)
    override val spoilered: Boolean = false,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(widthField)
    override val width: Int? = null,
    @SerialName(heightField)
    override val height: Int? = null,
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
) : DataRequest<ContentMessage<AnimationContent>>,
    SendContentMessageRequest<ContentMessage<AnimationContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<AnimationContent>>,
    TextableSendMessageRequest<ContentMessage<AnimationContent>>,
    ThumbedSendMessageRequest<ContentMessage<AnimationContent>>,
    DuratedSendMessageRequest<ContentMessage<AnimationContent>>,
    SizedSendMessageRequest<ContentMessage<AnimationContent>>,
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

    override fun method(): String = "sendAnimation"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<AnimationContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendAnimationFiles internal constructor(
    val animation: MultipartFile? = null,
    val thumbnail: MultipartFile? = null
) : Files by mapOfNotNull(
    animationField to animation,
    thumbnailField to thumbnail
)
