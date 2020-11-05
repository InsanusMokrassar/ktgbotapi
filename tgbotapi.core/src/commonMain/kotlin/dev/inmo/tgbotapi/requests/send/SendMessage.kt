package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.CommonAbstracts.types.DisableWebPagePreview
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.MessageEntity.RawMessageEntity
import dev.inmo.tgbotapi.types.MessageEntity.asTextParts
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

internal val TextContentMessageResultDeserializer: DeserializationStrategy<ContentMessage<TextContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

fun SendTextMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendTextMessage(
    chatId,
    text,
    parseMode,
    null,
    disableWebPagePreview,
    disableNotification,
    replyToMessageId,
    replyMarkup
)

fun SendTextMessage(
    chatId: ChatIdentifier,
    entities: List<TextSource>,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendTextMessage(
    chatId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    disableWebPagePreview,
    disableNotification,
    replyToMessageId,
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
    @SerialName(disableWebPagePreviewField)
    override val disableWebPagePreview: Boolean? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<ContentMessage<TextContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<TextContent>>,
    TextableSendMessageRequest<ContentMessage<TextContent>>,
    DisableWebPagePreview
{
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextParts(text) ?.justTextSources()
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
