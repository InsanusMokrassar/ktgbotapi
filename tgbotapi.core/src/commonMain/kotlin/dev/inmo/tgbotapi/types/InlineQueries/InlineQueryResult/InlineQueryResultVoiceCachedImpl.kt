package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoiceCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.inlineQueryResultVoiceType
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultVoiceCachedImpl(
    id: InlineQueryId,
    fileId: FileId,
    title: String,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
) = InlineQueryResultVoiceCachedImpl(id, fileId, title, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultVoiceCachedImpl(
    id: InlineQueryId,
    fileId: FileId,
    title: String,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
) = InlineQueryResultVoiceCachedImpl(
    id,
    fileId,
    title,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    replyMarkup,
    inputMessageContent,
)

@Serializable
data class InlineQueryResultVoiceCachedImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryId,
    @SerialName(voiceFileIdField)
    override val fileId: FileId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResultVoiceCached {
    override val type: String = inlineQueryResultVoiceType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
