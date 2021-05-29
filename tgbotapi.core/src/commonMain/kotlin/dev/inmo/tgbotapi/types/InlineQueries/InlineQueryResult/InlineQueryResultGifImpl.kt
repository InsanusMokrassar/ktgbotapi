package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.CommonAbstracts.makeString
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGif
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.inlineQueryResultGifType
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultGifImpl(
    id: InlineQueryIdentifier,
    url: String,
    thumbUrl: String,
    thumbMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(id, url, thumbUrl, thumbMimeType, width, height, duration, title, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultGifImpl(
    id: InlineQueryIdentifier,
    url: String,
    thumbUrl: String,
    thumbMimeType: MimeType? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    title: String? = null,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifImpl(id, url, thumbUrl, thumbMimeType, width, height, duration, title, entities.makeString(), null, entities.toRawMessageEntities(), replyMarkup, inputMessageContent)

@Serializable
data class InlineQueryResultGifImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(gifUrlField)
    override val url: String,
    @SerialName(thumbUrlField)
    override val thumbUrl: String,
    @SerialName(thumbMimeTypeField)
    override val thumbMimeType: MimeType? = null,
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
        if (thumbMimeType != null && thumbMimeType !in telegramInlineModeGifPermittedMimeTypes) {
            error("Passed thumb mime type is not permitted in Telegram Bot API. Passed $thumbMimeType, but permitted $telegramInlineModeGifPermittedMimeTypes")
        }
    }
}
