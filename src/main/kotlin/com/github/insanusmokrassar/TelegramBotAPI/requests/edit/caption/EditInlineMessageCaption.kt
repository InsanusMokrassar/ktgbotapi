package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.caption

import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

@Serializable
data class EditInlineMessageCaption(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier,
    @SerialName(captionField)
    override val text: String,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditTextChatMessage, EditReplyMessage {
    override fun method(): String = editMessageCaptionMethod
}
