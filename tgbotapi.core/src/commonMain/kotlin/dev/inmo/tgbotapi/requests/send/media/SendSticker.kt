package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.sendMessagePrimaryConstructorWarning
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyEphemeralSendRequest
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendContentMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.StickerContent
import kotlinx.serialization.*

@Suppress("FunctionName")
fun SendSticker(
    chatId: ChatIdentifier,
    sticker: InputFile,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ChatContentMessage<StickerContent>> = SendStickerByFileId(
    chatId = chatId,
    sticker = sticker,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
).withMultipartFiles(sticker)

/** Sends the message as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun SendSticker(
    ephemeralMessageParameters: EphemeralMessageParameters,
    sticker: InputFile,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ChatContentMessage<StickerContent>> = SendStickerByFileId(
    ephemeralMessageParameters = ephemeralMessageParameters,
    sticker = sticker,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
).withMultipartFiles(sticker)

private val commonResultDeserializer: DeserializationStrategy<ChatContentMessage<StickerContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@ConsistentCopyVisibility
@Serializable
data class SendStickerByFileId @Warning(sendMessagePrimaryConstructorWarning) internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier? = null,
    @SerialName(stickerField)
    val sticker: InputFile,
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
    @SerialName(emojiField)
    val emoji: String? = null,
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
) : SendContentMessageRequest<ChatContentMessage<StickerContent>>, ReplyingMarkupSendMessageRequest<ChatContentMessage<StickerContent>>, OptionallyEphemeralSendRequest {
    /** Sends to [chatId]. Set [receiverUserId] to deliver an ephemeral message to one user in the chat. */
    @OptIn(Warning::class)
    constructor(
        chatId: ChatIdentifier,
        sticker: InputFile,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        receiverUserId: UserId? = chatId.receiverUser,
        emoji: String? = null,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        sticker = sticker,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        ephemeralMessageParameters = receiverUserId?.let(::EphemeralMessageParameters),
        emoji = emoji,
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
        sticker: InputFile,
        threadId: MessageThreadId? = null,
        directMessageThreadId: DirectMessageThreadId? = null,
        businessConnectionId: BusinessConnectionId? = null,
        emoji: String? = null,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = null,
        sticker = sticker,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        ephemeralMessageParameters = ephemeralMessageParameters,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    init {
        requireSendMessageTarget(chatId, ephemeralMessageParameters)
    }

    override fun method(): String = "sendSticker"
    override val resultDeserializer: DeserializationStrategy<ChatContentMessage<StickerContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
