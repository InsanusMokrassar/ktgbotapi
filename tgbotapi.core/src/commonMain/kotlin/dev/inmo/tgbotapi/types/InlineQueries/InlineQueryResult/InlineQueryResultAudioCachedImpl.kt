@file:Suppress("unused")

package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudioCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.inlineQueryResultAudioType
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

fun InlineQueryResultAudioCachedImpl(
    id: InlineQueryId,
    fileId: FileId,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultAudioCachedImpl(id, fileId, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultAudioCachedImpl(
    id: InlineQueryId,
    fileId: FileId,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultAudioCachedImpl(
    id,
    fileId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    replyMarkup,
    inputMessageContent
)

@ConsistentCopyVisibility
@Serializable
data class InlineQueryResultAudioCachedImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryId,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(audioFileIdField)
    override val fileId: FileId,
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
) : InlineQueryResultAudioCached {
    override val type: String = inlineQueryResultAudioType
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
