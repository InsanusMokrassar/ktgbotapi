package dev.inmo.tgbotapi.requests.edit.media

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.TelegramFreeMedia
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

@Serializable
data class EditInlineMessageMedia(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageId,
    @SerialName(mediaField)
    override val media: TelegramFreeMedia,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditReplyMessage, EditMediaMessage {

    init {
        if (media.file is MultipartFile) {
            throw IllegalArgumentException("For editing of media messages you MUST use file id (according to documentation)")
        }
    }
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = editMessageMediaMethod
}
