package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.DisableWebPagePreview
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.*

@Serializable
data class SendMessage(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(textField)
    override val text: String,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @SerialName(disableWebPagePreviewField)
    @Optional
    override val disableWebPagePreview: Boolean? = null,
    @SerialName(disableNotificationField)
    @Optional
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    @Optional
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<RawMessage>,
    ReplyingMarkupSendMessageRequest<RawMessage>,
    TextableSendMessageRequest<RawMessage>,
    DisableWebPagePreview
{
    init {
        if (text.length !in textLength) {
            throw IllegalArgumentException("Text must be in $textLength range")
        }
    }

    override fun method(): String = "sendMessage"
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}
