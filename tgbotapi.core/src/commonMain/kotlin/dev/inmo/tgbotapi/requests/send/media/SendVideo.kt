@file:Suppress("FunctionName")

package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.abstracts.WithCustomStartMediaData
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.media.OptionallyStreamable
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
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
    supportsStreaming: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ChatContentMessage<VideoContent>> = SendVideoData(
    chatId = chatId,
    video = video,
    thumbnail = thumbnail ?.fileId,
    cover = cover,
    startTimestamp = startTimestamp,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
).withMultipartFiles(video, thumbnail, cover)

/** Sends the message as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun SendVideo(
    ephemeralMessageParameters: EphemeralMessageParameters,
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
    supportsStreaming: Boolean = false,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ChatContentMessage<VideoContent>> = SendVideoData(
    ephemeralMessageParameters = ephemeralMessageParameters,
    video = video,
    thumbnail = thumbnail ?.fileId,
    cover = cover,
    startTimestamp = startTimestamp,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
).withMultipartFiles(video, thumbnail, cover)

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
    supportsStreaming: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ChatContentMessage<VideoContent>> = SendVideoData(
    chatId = chatId,
    video = video,
    thumbnail = thumbnail ?.fileId,
    cover = cover,
    startTimestamp = startTimestamp,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
).withMultipartFiles(video, thumbnail, cover)

/** Sends the message as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun SendVideo(
    ephemeralMessageParameters: EphemeralMessageParameters,
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
    supportsStreaming: Boolean = false,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ChatContentMessage<VideoContent>> = SendVideoData(
    ephemeralMessageParameters = ephemeralMessageParameters,
    video = video,
    thumbnail = thumbnail ?.fileId,
    cover = cover,
    startTimestamp = startTimestamp,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
).withMultipartFiles(video, thumbnail, cover)

private val commonResultDeserializer: DeserializationStrategy<ChatContentMessage<VideoContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@ConsistentCopyVisibility
@Serializable
data class SendVideoData @Warning(sendMessagePrimaryConstructorWarning) internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier? = null,
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
    override val supportsStreaming: Boolean = false,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(messageThreadIdField)
    @EncodeDefault
    override val threadId: MessageThreadId? = chatId.threadId,
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault
    @SerialName(directMessagesTopicIdField)
    override val directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(ephemeralMessageParametersField)
    override val ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(allowPaidBroadcastField)
    override val allowPaidBroadcast: Boolean = false,
    @SerialName(messageEffectIdField)
    override val effectId: EffectId? = null,
    @SerialName(suggestedPostParametersField)
    override val suggestedPostParameters: SuggestedPostParameters? = null,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ChatContentMessage<VideoContent>>,
    SendContentMessageRequest<ChatContentMessage<VideoContent>>,
    ReplyingMarkupSendMessageRequest<ChatContentMessage<VideoContent>>,
    TextableSendMessageRequest<ChatContentMessage<VideoContent>>,
    ThumbedSendMessageRequest<ChatContentMessage<VideoContent>>,
    DuratedSendMessageRequest<ChatContentMessage<VideoContent>>,
    SizedSendMessageRequest<ChatContentMessage<VideoContent>>,
    WithCustomizableCaptionRequest<ChatContentMessage<VideoContent>>,
    CoveredSendMessageRequest<ChatContentMessage<VideoContent>>,
    WithCustomStartMediaData,
    OptionallyWithSpoilerRequest,
    OptionallyStreamable,
    OptionallyEphemeralSendRequest
{
    /** Sends to [chatId]. Set [receiverUserId] to deliver an ephemeral message to one user in the chat. */
    @OptIn(Warning::class)
    constructor(
        chatId: ChatIdentifier,
        video: InputFile,
        thumbnail: String? = null,
        cover: InputFile? = null,
        startTimestamp: Seconds? = null,
        text: String? = null,
        parseMode: ParseMode? = null,
        showCaptionAboveMedia: Boolean = false,
        spoilered: Boolean = false,
        duration: Long? = null,
        width: Int? = null,
        height: Int? = null,
        supportsStreaming: Boolean = false,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        receiverUserId: UserId? = chatId.receiverUser,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        video = video,
        thumbnail = thumbnail,
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
        supportsStreaming = supportsStreaming,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        ephemeralMessageParameters = receiverUserId?.let(::EphemeralMessageParameters),
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    /** Creates an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
    @OptIn(Warning::class)
    constructor(
        ephemeralMessageParameters: EphemeralMessageParameters,
        video: InputFile,
        thumbnail: String? = null,
        cover: InputFile? = null,
        startTimestamp: Seconds? = null,
        text: String? = null,
        parseMode: ParseMode? = null,
        showCaptionAboveMedia: Boolean = false,
        spoilered: Boolean = false,
        duration: Long? = null,
        width: Int? = null,
        height: Int? = null,
        supportsStreaming: Boolean = false,
        threadId: MessageThreadId? = null,
        directMessageThreadId: DirectMessageThreadId? = null,
        businessConnectionId: BusinessConnectionId? = null,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = null,
        video = video,
        thumbnail = thumbnail,
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
        supportsStreaming = supportsStreaming,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        ephemeralMessageParameters = ephemeralMessageParameters,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    /** Sends to [chatId]. Set [receiverUserId] to deliver an ephemeral message to one user in the chat. */
    @OptIn(Warning::class)
    constructor(
        chatId: ChatIdentifier,
        video: InputFile,
        thumbnail: String? = null,
        cover: InputFile? = null,
        startTimestamp: Seconds? = null,
        entities: TextSourcesList,
        showCaptionAboveMedia: Boolean = false,
        spoilered: Boolean = false,
        duration: Long? = null,
        width: Int? = null,
        height: Int? = null,
        supportsStreaming: Boolean = false,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        receiverUserId: UserId? = chatId.receiverUser,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        video = video,
        thumbnail = thumbnail,
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
        supportsStreaming = supportsStreaming,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        ephemeralMessageParameters = receiverUserId?.let(::EphemeralMessageParameters),
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    /** Creates an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
    @OptIn(Warning::class)
    constructor(
        ephemeralMessageParameters: EphemeralMessageParameters,
        video: InputFile,
        thumbnail: String? = null,
        cover: InputFile? = null,
        startTimestamp: Seconds? = null,
        entities: TextSourcesList,
        showCaptionAboveMedia: Boolean = false,
        spoilered: Boolean = false,
        duration: Long? = null,
        width: Int? = null,
        height: Int? = null,
        supportsStreaming: Boolean = false,
        threadId: MessageThreadId? = null,
        directMessageThreadId: DirectMessageThreadId? = null,
        businessConnectionId: BusinessConnectionId? = null,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = null,
        video = video,
        thumbnail = thumbnail,
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
        supportsStreaming = supportsStreaming,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        ephemeralMessageParameters = ephemeralMessageParameters,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    init {
        requireSendMessageTarget(chatId, ephemeralMessageParameters)
        text ?.let {
            if (it.length !in captionLength) {
                throwRangeError("Caption length", captionLength, it.length)
            }
        }
    }

    override fun method(): String = "sendVideo"
    override val resultDeserializer: DeserializationStrategy<ChatContentMessage<VideoContent>>
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
