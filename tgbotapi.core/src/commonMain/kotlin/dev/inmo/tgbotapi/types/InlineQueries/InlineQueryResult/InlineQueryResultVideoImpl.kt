package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.CommonAbstracts.makeString
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.inlineQueryResultVideoType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.files.abstracts.mimeTypeField
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultVideoImpl(
    id: InlineQueryIdentifier,
    url: String,
    thumbUrl: String,
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
) = InlineQueryResultVideoImpl(id, url, thumbUrl, mimeType, title, width, height, duration, description, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultVideoImpl(
    id: InlineQueryIdentifier,
    url: String,
    thumbUrl: String,
    mimeType: MimeType,
    title: String,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    description: String? = null,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultVideoImpl(id, url, thumbUrl, mimeType, title, width, height, duration, description, entities.makeString(), null, entities.toRawMessageEntities(), replyMarkup, inputMessageContent)

@Serializable
data class InlineQueryResultVideoImpl internal constructor(
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
