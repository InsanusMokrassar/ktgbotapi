package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoice
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.inlineQueryResultVoiceType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun InlineQueryResultVoiceImpl(
    id: InlineQueryIdentifier,
    url: String,
    title: String,
    duration: Int? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultVoiceImpl(
    id,
    url,
    title,
    duration,
    text,
    parseMode,
    null,
    replyMarkup,
    inputMessageContent
)

fun InlineQueryResultVoiceImpl(
    id: InlineQueryIdentifier,
    url: String,
    title: String,
    duration: Int? = null,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = InlineQueryResultVoiceImpl(id, url, title, duration, entities.makeString(), null, entities.toRawMessageEntities(), replyMarkup, inputMessageContent)

@Serializable
data class InlineQueryResultVoiceImpl internal constructor(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(voiceUrlField)
    override val url: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(voiceDurationField)
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
) : InlineQueryResultVoice {
    override val type: String = inlineQueryResultVoiceType
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
}
