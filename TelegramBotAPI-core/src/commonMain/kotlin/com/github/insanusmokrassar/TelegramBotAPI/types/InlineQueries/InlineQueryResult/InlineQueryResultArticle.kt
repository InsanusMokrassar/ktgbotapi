package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InlineQueryResultArticle(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(titleField)
    override val title: String,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(urlField)
    override val url: String? = null,
    @SerialName(hideUrlField)
    val hideUrl: Boolean? = null,
    @SerialName(descriptionField)
    override val description: String? = null,
    @SerialName(thumbUrlField)
    override val thumbUrl: String? = null,
    @SerialName(thumbWidthField)
    override val thumbWidth: Int? = null,
    @SerialName(thumbHeightField)
    override val thumbHeight: Int? = null
) : InlineQueryResult,
    ThumbSizedInlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    WithInputMessageContentInlineQueryResult,
    UrlInlineQueryResult {
    override val type: String = "article"
}