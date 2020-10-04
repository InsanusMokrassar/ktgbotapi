package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudio
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.inlineQueryResultAudioType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultAudioImpl(
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
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultAudio {
    override val type: String = inlineQueryResultAudioType
}
