package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocument
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.inlineQueryResultDocumentType
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

fun InlineQueryResultDocumentImpl(
    id: InlineQueryIdentifier,
    url: String,
    title: String,
    mimeType: MimeType,
    thumbnailUrl: String? = null,
    thumbnailWidth: Int? = null,
    thumbnailHeight: Int? = null,
    description: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultDocumentImpl(id, url, title, mimeType, thumbnailUrl, thumbnailWidth, thumbnailHeight, description, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultDocumentImpl(
    id: InlineQueryIdentifier,
    url: String,
    title: String,
    mimeType: MimeType,
    thumbnailUrl: String? = null,
    thumbnailWidth: Int? = null,
    thumbnailHeight: Int? = null,
    description: String? = null,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultDocumentImpl(
    id,
    url,
    title,
    mimeType,
    thumbnailUrl,
    thumbnailWidth,
    thumbnailHeight,
    description,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    replyMarkup,
    inputMessageContent
)

@Serializable
data class InlineQueryResultDocumentImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(documentUrlField)
    override val url: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType,
    @SerialName(thumbnailUrlField)
    override val thumbnailUrl: String? = null,
    @SerialName(thumbnailWidthField)
    override val thumbnailWidth: Int? = null,
    @SerialName(thumbnailHeightField)
    override val thumbnailHeight: Int? = null,
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
) : InlineQueryResultDocument {
    override val type: String = inlineQueryResultDocumentType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
