package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.DisableWebPagePreview
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
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
        if (text.length > maxTextLength) {
            throw IllegalArgumentException("Text length must be less than $maxTextLength, but was ${text.length}")
        }
    }

    override fun method(): String = "sendMessage"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<TextContent>>
        get() = TextContentMessageResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
