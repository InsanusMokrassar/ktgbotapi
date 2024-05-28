package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhoto
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.inlineQueryResultPhotoType
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultPhotoImpl(
    id: InlineQueryId,
    url: String,
    thumbnailUrl: String,
    width: Int? = null,
    height: Int? = null,
    title: String? = null,
    description: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultPhotoImpl(
    id = id,
    url = url,
    thumbnailUrl = thumbnailUrl,
    width = width,
    height = height,
    title = title,
    description = description,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
    inputMessageContent = inputMessageContent
)

fun InlineQueryResultPhotoImpl(
    id: InlineQueryId,
    url: String,
    thumbnailUrl: String,
    width: Int? = null,
    height: Int? = null,
    title: String? = null,
    description: String? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultPhotoImpl(
    id = id,
    url = url,
    thumbnailUrl = thumbnailUrl,
    width = width,
    height = height,
    title = title,
    description = description,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
    inputMessageContent = inputMessageContent
)

@Serializable
data class InlineQueryResultPhotoImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryId,
    @SerialName(photoUrlField)
    override val url: String,
    @SerialName(thumbnailUrlField)
    override val thumbnailUrl: String,
    @SerialName(photoWidthField)
    override val width: Int? = null,
    @SerialName(photoHeightField)
    override val height: Int? = null,
    @SerialName(titleField)
    override val title: String? = null,
    @SerialName(descriptionField)
    override val description: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(showCaptionAboveMediaField)
    override val showCaptionAboveMedia: Boolean = false,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultPhoto {
    override val type: String = inlineQueryResultPhotoType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
