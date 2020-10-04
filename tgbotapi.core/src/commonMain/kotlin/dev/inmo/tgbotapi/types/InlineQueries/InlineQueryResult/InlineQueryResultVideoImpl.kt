package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.inlineQueryResultVideoType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.files.abstracts.mimeTypeField
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultVideoImpl(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(videoUrlField)
    override val url: String,
    @SerialName(thumbUrlField)
    override val thumbUrl: String,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType,
    @SerialName(titleField)
    override val title: String,
    @SerialName(videoWidthField)
    override val width: Int? = null,
    @SerialName(videoHeightField)
    override val height: Int? = null,
    @SerialName(videoDurationField)
    override val duration: Int? = null,
    @SerialName(descriptionField)
    override val description: String? = null,
    @SerialName(captionField)
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultVideo {
    override val type: String = inlineQueryResultVideoType
}
