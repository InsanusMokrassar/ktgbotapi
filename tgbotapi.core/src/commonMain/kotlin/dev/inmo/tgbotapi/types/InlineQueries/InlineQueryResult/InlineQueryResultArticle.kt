package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InlineQueryResultArticle(
    @SerialName(idField)
    override val id: InlineQueryId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(urlField)
    override val url: String? = null,
    @SerialName(descriptionField)
    override val description: String? = null,
    @SerialName(thumbnailUrlField)
    override val thumbnailUrl: String? = null,
    @SerialName(thumbnailWidthField)
    override val thumbnailWidth: Int? = null,
    @SerialName(thumbnailHeightField)
    override val thumbnailHeight: Int? = null
) : InlineQueryResult,
    ThumbSizedInlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    WithInputMessageContentInlineQueryResult,
    UrlInlineQueryResult {
    override val type: String = "article"
    @Deprecated("Field hide_url has been deprecated in Bot API 8.2. Use empty url instead")
    val hideUrl: Boolean
        get() = url != null && url.isEmpty()

    @Deprecated("Field hide_url has been deprecated in Bot API 8.2. Use empty url instead")
    constructor(
        id: InlineQueryId,
        title: String,
        inputMessageContent: InputMessageContent,
        replyMarkup: InlineKeyboardMarkup? = null,
        url: String? = null,
        hideUrl: Boolean?,
        description: String? = null,
        thumbnailUrl: String? = null,
        thumbnailWidth: Int? = null,
        thumbnailHeight: Int? = null
    ) : this(
        id = id,
        title = title,
        inputMessageContent = inputMessageContent,
        replyMarkup = replyMarkup,
        url = url,
        description = description,
        thumbnailUrl = thumbnailUrl,
        thumbnailWidth = thumbnailWidth,
        thumbnailHeight = thumbnailHeight
    )
}
