package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
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