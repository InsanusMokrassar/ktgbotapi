package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class InlineQueryResultArticle(
    @SerialName(idField)
    override val id: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(urlField)
    @Optional
    val url: String? = null,
    @SerialName(hideUrlField)
    @Optional
    val hideUrl: Boolean? = null,
    @SerialName(descriptionField)
    @Optional
    override val description: String? = null,
    @SerialName(thumbUrlField)
    @Optional
    override val thumbUrl: String? = null,
    @SerialName(thumbWidthField)
    @Optional
    override val thumbWidth: Int? = null,
    @SerialName(thumbHeightField)
    @Optional
    override val thumbHeight: Int? = null
) : InlineQueryResult,
    ThumbSizedInlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    WithInputMessageContentInlineQueryResult {
    override val type: String = "article"
}