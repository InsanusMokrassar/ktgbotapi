package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.abstracts.WithCustomizableCaption
import dev.inmo.tgbotapi.abstracts.types.MessageAction
import dev.inmo.tgbotapi.abstracts.types.ProtectContent
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.WithCustomizableCaptionRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

// TODO:: Swap fromChatId and toChatId for more correct order of parameters

const val OrderChangingDeprecationWarn = "The order of parameters in this factory will be changed soon. To avoid unexpected behaviour, swap message id and target chat id parameters"

//fun CopyMessage(
//    toChatId: ChatIdentifier,
//    fromChatId: ChatIdentifier,
//    messageId: MessageId,
//    text: String? = null,
//    parseMode: ParseMode? = null,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChatId.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//) = CopyMessage(
//    toChatId = toChatId,
//    fromChatId = fromChatId,
//    messageId = messageId,
//    text = text,
//    parseMode = parseMode,
//    rawEntities = null,
//    showCaptionAboveMedia = showCaptionAboveMedia,
//    threadId = threadId,
//    disableNotification = disableNotification,
//    protectContent = protectContent,
//    allowPaidBroadcast = allowPaidBroadcast,
//    replyParameters = replyParameters,
//    replyMarkup = replyMarkup
//)
//
//fun CopyMessage(
//    toChatId: ChatIdentifier,
//    fromChatId: ChatIdentifier,
//    messageId: MessageId,
//    entities: List<TextSource>,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChatId.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//) = CopyMessage(
//    toChatId = toChatId,
//    fromChatId = fromChatId,
//    messageId = messageId,
//    text = entities.makeString(),
//    parseMode = null,
//    rawEntities = entities.toRawMessageEntities(),
//    showCaptionAboveMedia = showCaptionAboveMedia,
//    threadId = threadId,
//    disableNotification = disableNotification,
//    protectContent = protectContent,
//    allowPaidBroadcast = allowPaidBroadcast,
//    replyParameters = replyParameters,
//    replyMarkup = replyMarkup
//)

fun CopyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageId = messageId,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

fun CopyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    entities: List<TextSource>,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageId = messageId,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

@Serializable
data class CopyMessage internal constructor(
    @SerialName(chatIdField)
    val toChatId: ChatIdentifier,
    @SerialName(fromChatIdField)
    val fromChatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(showCaptionAboveMediaField)
    override val showCaptionAboveMedia: Boolean = false,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = toChatId.threadId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(allowPaidBroadcastField)
    override val allowPaidBroadcast: Boolean = false,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
): SimpleRequest<MessageId>,
    ReplyingMarkupSendMessageRequest<MessageId>,
    WithCustomizableCaptionRequest<MessageId>,
    MessageAction,
    TextedOutput,
    ProtectContent,
    OptionallyMessageThreadRequest {
    override val chatId: ChatIdentifier
        get() = fromChatId
    override val textSources: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    override fun method(): String = "copyMessage"

    override val resultDeserializer: DeserializationStrategy<MessageId>
        get() = MessageIdSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
