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
    id: InlineQueryIdentifier,
    url: String,
    thumbnailUrl: String,
    thumbnailMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(id, url, thumbnailUrl, thumbnailMimeType, width, height, duration, title, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultGifImpl(
    id: InlineQueryIdentifier,
    url: String,
    thumbnailUrl: String,
    thumbnailMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(
    id,
    url,
    thumbnailUrl,
    thumbnailMimeType,
    width,
    height,
    duration,
    title,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    replyMarkup,
    inputMessageContent
)

fun InlineQueryResultGifImpl(
    id: InlineQueryIdentifier,
    gifFile: FileId,
    thumbnailUrl: String,
    thumbnailMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(id, gifFile.fileId, thumbnailUrl, thumbnailMimeType, width, height, duration, title, text, parseMode, replyMarkup, inputMessageContent)

fun InlineQueryResultGifImpl(
    id: InlineQueryIdentifier,
    gifFile: FileId,
    thumbnailUrl: String,
    thumbnailMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(
    id, gifFile.fileId, thumbnailUrl, thumbnailMimeType, width, height, duration, title, entities, replyMarkup, inputMessageContent
)

@Serializable
data class InlineQueryResultGifImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
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
