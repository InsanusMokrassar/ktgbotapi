package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.CommonAbstracts.makeString
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideoCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.inlineQueryResultVideoType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultVideoCachedImpl(
    id: InlineQueryIdentifier,
    fileId: FileId,
    title: String,
    description: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultVideoCachedImpl(id, fileId, title, description, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultVideoCachedImpl(
    id: InlineQueryIdentifier,
    fileId: FileId,
    title: String,
    description: String? = null,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultVideoCachedImpl(id, fileId, title, description, entities.makeString(), null, entities.toRawMessageEntities(), replyMarkup, inputMessageContent)

@Serializable
data class InlineQueryResultVideoCachedImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(videoFileIdField)
    override val fileId: FileId,
    @SerialName(titleField)
    override val title: String,
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
) : InlineQueryResultVideoCached {
    override val type: String = inlineQueryResultVideoType
    override val textSources: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
