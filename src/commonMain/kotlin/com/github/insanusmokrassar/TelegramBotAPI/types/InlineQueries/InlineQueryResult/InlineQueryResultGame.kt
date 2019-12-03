package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.gameShortNameField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InlineQueryResultGame(
    override val id: String,
    @SerialName(gameShortNameField)
    val gameShortName: String,
    override val replyMarkup: InlineKeyboardMarkup? = null
) : InlineQueryResult {
    override val type: String = "game"
}