package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput
import dev.inmo.tgbotapi.CommonAbstracts.types.MessageAction
import dev.inmo.tgbotapi.CommonAbstracts.types.ProtectContent
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

fun CopyMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(fromChatId, toChatId, messageId, text, parseMode, null, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)

fun CopyMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = CopyMessage(
    fromChatId,
    toChatId,
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
    @SerialName(fromChatIdField)
    val fromChatId: ChatIdentifier,
    @SerialName(chatIdField)
    val toChatId: ChatIdentifier,
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
