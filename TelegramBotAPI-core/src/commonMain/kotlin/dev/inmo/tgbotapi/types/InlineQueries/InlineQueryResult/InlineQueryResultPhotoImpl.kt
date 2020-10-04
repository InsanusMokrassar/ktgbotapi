package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhoto
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.inlineQueryResultPhotoType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultPhotoImpl(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(photoUrlField)
    override val url: String,
    @SerialName(thumbUrlField)
    override val thumbUrl: String,
    @SerialName(photoWidthField)
    override val width: Int? = null,
    @SerialName(photoHeightField)
    override val height: Int? = null,
    @SerialName(titleField)
    override val title: String? = null,
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
) : InlineQueryResultPhoto {
    override val type: String = inlineQueryResultPhotoType
}
