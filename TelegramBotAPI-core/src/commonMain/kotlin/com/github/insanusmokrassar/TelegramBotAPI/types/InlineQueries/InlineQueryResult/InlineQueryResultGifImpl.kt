package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGif
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.gif.inlineQueryResultGifType
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultGifImpl(
    @SerialName(idField)
    override val id: String,
    @SerialName(gifUrlField)
    override val url: String,
    @SerialName(thumbUrlField)
    override val thumbUrl: String,
    @SerialName(thumbMimeTypeField)
    override val thumbMimeType: MimeType? = null,
    @SerialName(gifWidthField)
    override val width: Int? = null,
    @SerialName(gifHeightField)
    override val height: Int? = null,
    @SerialName(gifDurationField)
    override val duration: Int? = null,
    @SerialName(titleField)
    override val title: String? = null,
    @SerialName(captionField)
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultGif {
    override val type: String = inlineQueryResultGifType

    init {
        if (thumbMimeType != null && thumbMimeType !in telegramInlineModeGifPermittedMimeTypes) {
            error("Passed thumb mime type is not permitted in Telegram Bot API. Passed $thumbMimeType, but permitted $telegramInlineModeGifPermittedMimeTypes")
        }
    }
}
