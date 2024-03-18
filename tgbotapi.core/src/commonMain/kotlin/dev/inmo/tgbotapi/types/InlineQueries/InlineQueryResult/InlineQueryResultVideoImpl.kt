package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.inlineQueryResultVideoType
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.MimeType
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultVideoImpl(
    id: InlineQueryId,
    url: String,
    thumbnailUrl: String,
    mimeType: MimeType,
    title: String,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    description: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultVideoImpl(id, url, thumbnailUrl, mimeType, title, width, height, duration, description, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultVideoImpl(
    id: InlineQueryId,
    url: String,
    thumbnailUrl: String,
    mimeType: MimeType,
    title: String,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    description: String? = null,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultVideoImpl(
    id,
    url,
    thumbnailUrl,
    mimeType,
    title,
    width,
    height,
    duration,
    description,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    replyMarkup,
    inputMessageContent
)

@Serializable
data class InlineQueryResultVideoImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryId,
    @SerialName(videoUrlField)
    override val url: String,
    @SerialName(thumbnailUrlField)
    override val thumbnailUrl: String,
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
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultVideo {
    override val type: String = inlineQueryResultVideoType
    override val textSources: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
