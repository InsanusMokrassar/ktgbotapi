package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.text

import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.*

const val editMessageTextMethod = "editMessageText"

@Serializable
data class EditChatMessageText(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
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
) : EditChatMessage, EditTextChatMessage, EditReplyMessage, EditDisableWebPagePreviewMessage {

    override fun method(): String = editMessageTextMethod
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}
