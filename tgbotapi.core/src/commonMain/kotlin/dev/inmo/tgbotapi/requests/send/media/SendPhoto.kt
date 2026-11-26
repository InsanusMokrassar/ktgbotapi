@file:Suppress("FunctionName")

package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.requests.abstracts.*
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
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
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
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
): Request<ChatContentMessage<PhotoContent>> = SendPhotoData(
    chatId = chatId,
    photo = photo,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
).withMultipartFiles(photo)

/** Sends the message as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun SendPhoto(
    ephemeralMessageParameters: EphemeralMessageParameters,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
): Request<ChatContentMessage<PhotoContent>> = SendPhotoData(
    ephemeralMessageParameters = ephemeralMessageParameters,
    photo = photo,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
).withMultipartFiles(photo)

fun SendPhoto(
    chatId: ChatIdentifier,
    photo: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
): Request<ChatContentMessage<PhotoContent>> = SendPhotoData(
    chatId = chatId,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
).withMultipartFiles(photo)

/** Sends the message as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun SendPhoto(
    ephemeralMessageParameters: EphemeralMessageParameters,
    photo: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
): Request<ChatContentMessage<PhotoContent>> = SendPhotoData(
    ephemeralMessageParameters = ephemeralMessageParameters,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
).withMultipartFiles(photo)

private val commonResultDeserializer: DeserializationStrategy<ChatContentMessage<PhotoContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@ConsistentCopyVisibility
@Serializable
data class SendPhotoData @Warning(sendMessagePrimaryConstructorWarning) internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier? = null,
    @SerialName(photoField)
    val photo: InputFile,
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
) : DataRequest<ChatContentMessage<PhotoContent>>,
    SendContentMessageRequest<ChatContentMessage<PhotoContent>>,
    ReplyingMarkupSendMessageRequest<ChatContentMessage<PhotoContent>>,
    TextableSendMessageRequest<ChatContentMessage<PhotoContent>>,
    WithCustomizableCaptionRequest<ChatContentMessage<PhotoContent>>,
    OptionallyWithSpoilerRequest,
    OptionallyEphemeralSendRequest
{
    /** Sends to [chatId]. Set [receiverUserId] to deliver an ephemeral message to one user in the chat. */
    @OptIn(Warning::class)
    constructor(
        chatId: ChatIdentifier,
        photo: InputFile,
        text: String? = null,
        parseMode: ParseMode? = null,
        showCaptionAboveMedia: Boolean = false,
        spoilered: Boolean = false,
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
        photo = photo,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
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
        photo: InputFile,
        text: String? = null,
        parseMode: ParseMode? = null,
        showCaptionAboveMedia: Boolean = false,
        spoilered: Boolean = false,
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
        photo = photo,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
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
        photo: InputFile,
        entities: TextSourcesList,
        showCaptionAboveMedia: Boolean = false,
        spoilered: Boolean = false,
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
        photo = photo,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
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
        photo: InputFile,
        entities: TextSourcesList,
        showCaptionAboveMedia: Boolean = false,
        spoilered: Boolean = false,
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
        photo = photo,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
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

    override fun method(): String = "sendPhoto"
    override val resultDeserializer: DeserializationStrategy<ChatContentMessage<PhotoContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Suppress("unused")
@ConsistentCopyVisibility
data class SendPhotoFiles internal constructor(
    val photo: MultipartFile
) : Files by mapOf(
    photoField to photo
)
