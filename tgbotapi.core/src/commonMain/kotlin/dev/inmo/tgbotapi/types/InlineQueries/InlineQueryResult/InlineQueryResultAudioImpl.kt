package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.CommonAbstracts.makeString
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudio
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.inlineQueryResultAudioType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultAudioImpl(
    id: InlineQueryIdentifier,
    url: String,
    title: String,
    performer: String? = null,
    duration: Int? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultAudioImpl(id, url, title, performer, duration, text, parseMode, null, replyMarkup, inputMessageContent)

fun InlineQueryResultAudioImpl(
    id: InlineQueryIdentifier,
    url: String,
    title: String,
    performer: String? = null,
    duration: Int? = null,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultAudioImpl(id, url, title, performer, duration, entities.makeString(), null, entities.toRawMessageEntities(), replyMarkup, inputMessageContent)

@Serializable
data class InlineQueryResultAudioImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(audioUrlField)
    override val url: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(performerField)
    override val performer: String? = null,
    @SerialName(audioDurationField)
    override val duration: Int? = null,
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
) : InlineQueryResultAudio {
    override val type: String = inlineQueryResultAudioType
    override val textSources: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
