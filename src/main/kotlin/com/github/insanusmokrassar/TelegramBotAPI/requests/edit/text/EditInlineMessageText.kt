package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.text

import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.media.editMessageMediaMethod
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.*

@Serializable
data class EditInlineMessageText(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier,
    @SerialName(textField)
    override val text: String,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @SerialName(disableWebPagePreviewField)
    @Optional
    override val disableWebPagePreview: Boolean? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditTextChatMessage, EditReplyMessage, EditDisableWebPagePreviewMessage {
    override fun method(): String = editMessageMediaMethod
}
