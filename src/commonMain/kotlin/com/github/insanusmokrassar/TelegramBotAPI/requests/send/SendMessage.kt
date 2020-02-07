package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.DisableWebPagePreview
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import kotlinx.serialization.*

internal val TextContentMessageResultDeserializer: DeserializationStrategy<ContentMessage<TextContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendTextMessage(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(textField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
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
    init {
        if (text.length !in textLength) {
            throw IllegalArgumentException("Text must be in $textLength range")
        }
    }

    override fun method(): String = "sendMessage"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<TextContent>>
        get() = TextContentMessageResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Deprecated(
    "This declaration is deprecated due violation of common naming rules",
    ReplaceWith(
        "SendTextMessage"
    )
)
typealias SendMessage = SendTextMessage

suspend fun RequestsExecutor.sendMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendTextMessage(chatId, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun RequestsExecutor.sendTextMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(
    chatId, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(chat.id, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, replyMarkup)


suspend fun RequestsExecutor.sendTextMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chat.id, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, replyMarkup)
