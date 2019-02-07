package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGif
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.gif.inlineQueryResultGifType
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

@Serializable
data class InlineQueryResultGifImpl(
    @SerialName(idField)
    override val id: String,
    @SerialName(gifUrlField)
    override val url: String,
    @SerialName(thumbUrlField)
    override val thumbUrl: String,
    @SerialName(gifWidthField)
    @Optional
    override val width: Int? = null,
    @SerialName(gifHeightField)
    @Optional
    override val height: Int? = null,
    @SerialName(gifDurationField)
    @Optional
    override val duration: Int? = null,
    @SerialName(titleField)
    @Optional
    override val title: String? = null,
    @SerialName(captionField)
    @Optional
    override val caption: String? = null,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    @Optional
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultGif {
    override val type: String = inlineQueryResultGifType
}
