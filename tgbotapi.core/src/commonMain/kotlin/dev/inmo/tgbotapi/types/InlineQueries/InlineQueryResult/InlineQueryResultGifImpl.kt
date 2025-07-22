@file:Suppress("unused")

package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGif
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.inlineQueryResultGifType
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
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

fun InlineQueryResultGifImpl(
    id: InlineQueryId,
    url: String,
    thumbnailUrl: String,
    thumbnailMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(
    id = id,
    url = url,
    thumbnailUrl = thumbnailUrl,
    thumbnailMimeType = thumbnailMimeType,
    width = width,
    height = height,
    duration = duration,
    title = title,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
    inputMessageContent = inputMessageContent
)

fun InlineQueryResultGifImpl(
    id: InlineQueryId,
    url: String,
    thumbnailUrl: String,
    thumbnailMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(
    id = id,
    url = url,
    thumbnailUrl = thumbnailUrl,
    thumbnailMimeType = thumbnailMimeType,
    width = width,
    height = height,
    duration = duration,
    title = title,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
    inputMessageContent = inputMessageContent
)

fun InlineQueryResultGifImpl(
    id: InlineQueryId,
    gifFile: FileId,
    thumbnailUrl: String,
    thumbnailMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(
    id = id,
    url = gifFile.fileId,
    thumbnailUrl = thumbnailUrl,
    thumbnailMimeType = thumbnailMimeType,
    width = width,
    height = height,
    duration = duration,
    title = title,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
    inputMessageContent = inputMessageContent
)

fun InlineQueryResultGifImpl(
    id: InlineQueryId,
    gifFile: FileId,
    thumbnailUrl: String,
    thumbnailMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(
    id = id,
    url = gifFile.fileId,
    thumbnailUrl = thumbnailUrl,
    thumbnailMimeType = thumbnailMimeType,
    width = width,
    height = height,
    duration = duration,
    title = title,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
    inputMessageContent = inputMessageContent
)

@ConsistentCopyVisibility
@Serializable
data class InlineQueryResultGifImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryId,
    @SerialName(gifUrlField)
    override val url: String,
    @SerialName(thumbnailUrlField)
    override val thumbnailUrl: String,
    @SerialName(thumbnailMimeTypeField)
    override val thumbnailMimeType: MimeType? = null,
    @SerialName(gifWidthField)
    override val width: Int? = null,
    @SerialName(gifHeightField)
    override val height: Int? = null,
    @SerialName(gifDurationField)
    override val duration: Int? = null,
    @SerialName(titleField)
    override val title: String? = null,
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
) : InlineQueryResultGif {
    override val type: String = inlineQueryResultGifType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    init {
        if (thumbnailMimeType != null && thumbnailMimeType !in telegramInlineModeGifPermittedMimeTypes) {
            error("Passed thumb mime type is not permitted in Telegram Bot API. Passed $thumbnailMimeType, but permitted $telegramInlineModeGifPermittedMimeTypes")
        }
    }
}
