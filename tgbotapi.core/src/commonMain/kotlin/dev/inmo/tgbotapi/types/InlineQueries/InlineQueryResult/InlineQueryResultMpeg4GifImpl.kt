package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4Gif
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.inlineQueryResultMpeg4GifType
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

fun InlineQueryResultMpeg4GifImpl(
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
) = InlineQueryResultMpeg4GifImpl(id, url, thumbnailUrl, thumbnailMimeType, width, height, duration, title, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultMpeg4GifImpl(
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
) = InlineQueryResultMpeg4GifImpl(
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

@Serializable
data class InlineQueryResultMpeg4GifImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(mpeg4GifUrlField)
    override val url: String,
    @SerialName(thumbnailUrlField)
    override val thumbnailUrl: String,
    @SerialName(thumbnailMimeTypeField)
    override val thumbnailMimeType: MimeType? = null,
    @SerialName(mpeg4GifWidthField)
    override val width: Int? = null,
    @SerialName(mpeg4GifHeightField)
    override val height: Int? = null,
    @SerialName(mpeg4GifDurationField)
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
) : InlineQueryResultMpeg4Gif {
    override val type: String = inlineQueryResultMpeg4GifType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    init {
        if (thumbnailMimeType != null && thumbnailMimeType !in telegramInlineModeGifPermittedMimeTypes) {
            error("Passed thumb mime type is not permitted in Telegram Bot API. Passed $thumbnailMimeType, but permitted $telegramInlineModeGifPermittedMimeTypes")
        }
    }
}
