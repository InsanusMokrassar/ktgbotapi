package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhotoCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.inlineQueryResultPhotoType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultPhotoCachedImpl(
    id: InlineQueryIdentifier,
    fileId: FileId,
    title: String? = null,
    description: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultPhotoCachedImpl(id, fileId, title, description, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultPhotoCachedImpl(
    id: InlineQueryIdentifier,
    fileId: FileId,
    title: String? = null,
    description: String? = null,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultPhotoCachedImpl(id, fileId, title, description, entities.makeString(), null, entities.toRawMessageEntities(), replyMarkup, inputMessageContent)

@Serializable
data class InlineQueryResultPhotoCachedImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(photoFileIdField)
    override val fileId: FileId,
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
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultPhotoCached {
    override val type: String = inlineQueryResultPhotoType
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}