package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4GifCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.inlineQueryResultMpeg4GifType
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

fun InlineQueryResultMpeg4GifCachedImpl(
    id: InlineQueryId,
    fileId: FileId,
    title: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultMpeg4GifCachedImpl(
    id = id,
    fileId = fileId,
    title = title,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
    inputMessageContent = inputMessageContent
)

fun InlineQueryResultMpeg4GifCachedImpl(
    id: InlineQueryId,
    fileId: FileId,
    title: String? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultMpeg4GifCachedImpl(
    id = id,
    fileId = fileId,
    title = title,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
    inputMessageContent = inputMessageContent
)

@Serializable
data class InlineQueryResultMpeg4GifCachedImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryId,
    @SerialName(mpeg4GifFileIdField)
    override val fileId: FileId,
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
) : InlineQueryResultMpeg4GifCached {
    override val type: String = inlineQueryResultMpeg4GifType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
