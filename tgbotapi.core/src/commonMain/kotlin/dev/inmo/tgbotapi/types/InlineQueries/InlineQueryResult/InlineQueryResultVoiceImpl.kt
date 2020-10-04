package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoice
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.inlineQueryResultVoiceType
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultVoiceImpl(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(voiceUrlField)
    override val url: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(voiceDurationField)
    override val duration: Int? = null,
    @SerialName(captionField)
    override val caption: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultVoice {
    override val type: String = inlineQueryResultVoiceType
}
