package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.types.LinkPreviewOptionsContainer
import dev.inmo.tgbotapi.requests.send.abstracts.*
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
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

internal val TextContentMessageResultDeserializer: DeserializationStrategy<ContentMessage<TextContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

fun SendTextMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendTextMessage(
    chatId,
    text,
    parseMode,
    null,
    threadId,
    businessConnectionId,
    linkPreviewOptions,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

fun SendTextMessage(
    chatId: ChatIdentifier,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendTextMessage(
    chatId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    threadId,
    businessConnectionId,
    linkPreviewOptions,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

@Serializable
data class SendTextMessage internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(textField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(entitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(linkPreviewOptionsField)
    override val linkPreviewOptions: LinkPreviewOptions? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendContentMessageRequest<ContentMessage<TextContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<TextContent>>,
    TextableSendMessageRequest<ContentMessage<TextContent>>,
    LinkPreviewOptionsContainer
{
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    init {
        if (text.length !in textLength) {
            throwRangeError("Text length", textLength, text.length)
        }
    }

    override fun method(): String = "sendMessage"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<TextContent>>
        get() = TextContentMessageResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
