package dev.inmo.tgbotapi.requests.edit.reply_markup

import dev.inmo.tgbotapi.requests.edit.abstracts.EditInlineMessage
import dev.inmo.tgbotapi.requests.edit.abstracts.EditReplyMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

@Serializable
data class EditInlineMessageReplyMarkup(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageId,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditReplyMessage {
    override fun method(): String = editMessageReplyMarkupMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
