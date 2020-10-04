package dev.inmo.tgbotapi.requests.edit.text

import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.requests.edit.media.editMessageMediaMethod
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

@Serializable
data class EditInlineMessageText(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier,
    @SerialName(textField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(disableWebPagePreviewField)
    override val disableWebPagePreview: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditTextChatMessage, EditReplyMessage, EditDisableWebPagePreviewMessage {
    override fun method(): String = editMessageMediaMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
