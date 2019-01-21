package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.text

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.media.editMessageMediaMethod
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.*

@Serializable
data class EditInlineMessageMedia(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier,
    @SerialName(mediaField)
    override val media: InputMedia,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditReplyMessage, EditMediaMessage {

    init {
        if (media.file is MultipartFile) {
            throw IllegalArgumentException("For editing of media messages you MUST use file id (according to documentation)")
        }
    }

    override fun method(): String = editMessageMediaMethod
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}
