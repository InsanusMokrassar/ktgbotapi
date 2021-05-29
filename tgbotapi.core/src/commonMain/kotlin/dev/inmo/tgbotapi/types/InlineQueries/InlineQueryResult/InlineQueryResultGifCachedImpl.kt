package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.CommonAbstracts.makeString
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGifCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.inlineQueryResultGifType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultGifCachedImpl(
    id: InlineQueryIdentifier,
    fileId: FileId,
    title: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifCachedImpl(id, fileId, title, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultGifCachedImpl(
    id: InlineQueryIdentifier,
    fileId: FileId,
    title: String? = null,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultGifCachedImpl(id, fileId, title, entities.makeString(), null, entities.toRawMessageEntities(), replyMarkup, inputMessageContent)

@Serializable
data class InlineQueryResultGifCachedImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(gifFileIdField)
    override val fileId: FileId,
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
) : InlineQueryResultGifCached {
    override val type: String = inlineQueryResultGifType
    override val textSources: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
