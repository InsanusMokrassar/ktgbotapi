@file:Suppress("unused", "FunctionName")

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
import dev.inmo.tgbotapi.types.message.content.DocumentContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.mapOfNotNull
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

/**
 * Use this method to send general files. On success, the sent [ContentMessage] with [DocumentContent] is returned.
 * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
 *
 * @param disableContentTypeDetection Disables automatic server-side content type detection for [document] [MultipartFile]
 *
 * @see ContentMessage
 * @see DocumentContent
 */
fun SendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumbnail: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
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
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): Request<ChatContentMessage<DocumentContent>> = SendDocumentData(
    chatId = chatId,
    document = document,
    thumbnail = thumbnail ?.fileId,
    text = text,
    parseMode = parseMode,
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
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
).withMultipartFiles(document, thumbnail)

/** Sends the message as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun SendDocument(
    ephemeralMessageParameters: EphemeralMessageParameters,
    document: InputFile,
    thumbnail: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): Request<ChatContentMessage<DocumentContent>> = SendDocumentData(
    ephemeralMessageParameters = ephemeralMessageParameters,
    document = document,
    thumbnail = thumbnail ?.fileId,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
).withMultipartFiles(document, thumbnail)

/**
 * Use this method to send general files. On success, the sent [ContentMessage] with [DocumentContent] is returned.
 * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
 *
 * @param disableContentTypeDetection Disables automatic server-side content type detection for [document] [MultipartFile]
 *
 * @see ContentMessage
 * @see DocumentContent
 */
fun SendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumbnail: InputFile? = null,
    entities: TextSourcesList,
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
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): Request<ChatContentMessage<DocumentContent>> = SendDocumentData(
    chatId = chatId,
    document = document,
    thumbnail = thumbnail ?.fileId,
    entities = entities,
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
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
).withMultipartFiles(document, thumbnail)

/** Sends the message as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun SendDocument(
    ephemeralMessageParameters: EphemeralMessageParameters,
    document: InputFile,
    thumbnail: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): Request<ChatContentMessage<DocumentContent>> = SendDocumentData(
    ephemeralMessageParameters = ephemeralMessageParameters,
    document = document,
    thumbnail = thumbnail ?.fileId,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
).withMultipartFiles(document, thumbnail)

private val commonResultDeserializer: DeserializationStrategy<ChatContentMessage<DocumentContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

/**
 * Use this method to send general files. On success, the sent [ContentMessage] with [DocumentContent] is returned.
 * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
 *
 * @param disableContentTypeDetection Disables automatic server-side content type detection for [document] [MultipartFile]
 *
 * @see ContentMessage
 * @see DocumentContent
 */
@ConsistentCopyVisibility
@Serializable
data class SendDocumentData @Warning(sendMessagePrimaryConstructorWarning) internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier? = null,
    @SerialName(documentField)
    val document: InputFile,
    @SerialName(thumbnailField)
    override val thumbnail: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
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
    override val replyMarkup: KeyboardMarkup? = null,
    @SerialName(disableContentTypeDetectionField)
    val disableContentTypeDetection: Boolean? = null
) : DataRequest<ChatContentMessage<DocumentContent>>,
    SendContentMessageRequest<ChatContentMessage<DocumentContent>>,
    ReplyingMarkupSendMessageRequest<ChatContentMessage<DocumentContent>>,
    TextableSendMessageRequest<ChatContentMessage<DocumentContent>>,
    ThumbedSendMessageRequest<ChatContentMessage<DocumentContent>>,
    OptionallyEphemeralSendRequest
{
    /** Sends to [chatId]. Set [receiverUserId] to deliver an ephemeral message to one user in the chat. */
    @OptIn(Warning::class)
    constructor(
        chatId: ChatIdentifier,
        document: InputFile,
        thumbnail: String? = null,
        text: String? = null,
        parseMode: ParseMode? = null,
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
        replyMarkup: KeyboardMarkup? = null,
        disableContentTypeDetection: Boolean? = null
    ) : this(
        chatId = chatId,
        document = document,
        thumbnail = thumbnail,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
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
        replyMarkup = replyMarkup,
        disableContentTypeDetection = disableContentTypeDetection
    )

    /** Creates an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
    @OptIn(Warning::class)
    constructor(
        ephemeralMessageParameters: EphemeralMessageParameters,
        document: InputFile,
        thumbnail: String? = null,
        text: String? = null,
        parseMode: ParseMode? = null,
        threadId: MessageThreadId? = null,
        directMessageThreadId: DirectMessageThreadId? = null,
        businessConnectionId: BusinessConnectionId? = null,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null,
        disableContentTypeDetection: Boolean? = null
    ) : this(
        chatId = null,
        document = document,
        thumbnail = thumbnail,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
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
        replyMarkup = replyMarkup,
        disableContentTypeDetection = disableContentTypeDetection
    )

    /** Sends to [chatId]. Set [receiverUserId] to deliver an ephemeral message to one user in the chat. */
    @OptIn(Warning::class)
    constructor(
        chatId: ChatIdentifier,
        document: InputFile,
        thumbnail: String? = null,
        entities: TextSourcesList,
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
        replyMarkup: KeyboardMarkup? = null,
        disableContentTypeDetection: Boolean? = null
    ) : this(
        chatId = chatId,
        document = document,
        thumbnail = thumbnail,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
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
        replyMarkup = replyMarkup,
        disableContentTypeDetection = disableContentTypeDetection
    )

    /** Creates an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
    @OptIn(Warning::class)
    constructor(
        ephemeralMessageParameters: EphemeralMessageParameters,
        document: InputFile,
        thumbnail: String? = null,
        entities: TextSourcesList,
        threadId: MessageThreadId? = null,
        directMessageThreadId: DirectMessageThreadId? = null,
        businessConnectionId: BusinessConnectionId? = null,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null,
        disableContentTypeDetection: Boolean? = null
    ) : this(
        chatId = null,
        document = document,
        thumbnail = thumbnail,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
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
        replyMarkup = replyMarkup,
        disableContentTypeDetection = disableContentTypeDetection
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

    override fun method(): String = "sendDocument"
    override val resultDeserializer: DeserializationStrategy<ChatContentMessage<DocumentContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@ConsistentCopyVisibility
data class SendDocumentFiles internal constructor(
    val document: MultipartFile? = null,
    val thumbnail: MultipartFile? = null
) : Files by mapOfNotNull(
    documentField to document,
    thumbnailField to thumbnail
)
