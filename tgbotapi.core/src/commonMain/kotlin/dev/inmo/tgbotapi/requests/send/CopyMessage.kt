package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.abstracts.types.MessageAction
import dev.inmo.tgbotapi.abstracts.types.ProtectContent
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
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

fun CopyMessage(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    businessConnectionId: BusinessConnectionId? = toChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId,
    fromChatId,
    messageId,
    text,
    parseMode,
    null,
    threadId,
    businessConnectionId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

fun CopyMessage(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    entities: List<TextSource>,
    threadId: MessageThreadId? = toChatId.threadId,
    businessConnectionId: BusinessConnectionId? = toChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId,
    fromChatId,
    messageId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    threadId,
    businessConnectionId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

fun CopyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    businessConnectionId: BusinessConnectionId? = toChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId,
    fromChatId,
    messageId,
    text,
    parseMode,
    null,
    threadId,
    businessConnectionId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

fun CopyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    entities: List<TextSource>,
    threadId: MessageThreadId? = toChatId.threadId,
    businessConnectionId: BusinessConnectionId? = toChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId,
    fromChatId,
    messageId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    threadId,
    businessConnectionId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
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
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = toChatId.threadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = toChatId.businessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
): SimpleRequest<MessageId>,
    ReplyingMarkupSendMessageRequest<MessageId>,
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
