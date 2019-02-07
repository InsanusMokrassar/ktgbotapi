package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.gif.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultGifCachedImpl(
    @SerialName(idField)
    override val id: String,
    @SerialName(gifFileIdField)
    override val fileId: FileId,
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
) : InlineQueryResultGifCached {
    override val type: String = inlineQueryResultGifType
}
