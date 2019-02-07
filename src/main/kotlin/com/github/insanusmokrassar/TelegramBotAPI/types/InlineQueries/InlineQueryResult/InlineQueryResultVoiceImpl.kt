package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoice
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultVoiceImpl(
    @SerialName(idField)
    override val id: String,
    @SerialName(voiceUrlField)
    override val url: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(voiceDurationField)
    @Optional
    override val duration: Int? = null,
    @SerialName(captionField)
    @Optional
    override val caption: String? = null,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    @Optional
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultVoice
