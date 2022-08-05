package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.abstracts.types.MessageAction
import dev.inmo.tgbotapi.abstracts.types.ProtectContent
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.types.*
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
    messageId: MessageIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId,
    fromChatId,
    messageId,
    text,
    parseMode,
    null,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

fun CopyMessage(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId,
    fromChatId,
    messageId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

fun CopyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    toChatId: ChatIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId,
    fromChatId,
    messageId,
    text,
    parseMode,
    null,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

fun CopyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    toChatId: ChatIdentifier,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    toChatId,
    fromChatId,
    messageId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

@Serializable
data class CopyMessage internal constructor(
    @SerialName(chatIdField)
    val toChatId: ChatIdentifier,
    @SerialName(fromChatIdField)
    val fromChatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
): SimpleRequest<MessageIdentifier>,
    ReplyingMarkupSendMessageRequest<MessageIdentifier>,
    MessageAction,
    TextedOutput,
    ProtectContent {
    override val chatId: ChatIdentifier
        get() = fromChatId
    override val textSources: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    override fun method(): String = "copyMessage"

    override val resultDeserializer: DeserializationStrategy<MessageIdentifier>
        get() = MessageIdSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
