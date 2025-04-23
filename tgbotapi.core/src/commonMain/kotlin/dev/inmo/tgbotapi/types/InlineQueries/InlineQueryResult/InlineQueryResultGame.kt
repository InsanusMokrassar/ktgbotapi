package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueryId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.gameShortNameField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InlineQueryResultGame(
    override val id: InlineQueryId,
    @SerialName(gameShortNameField)
    val gameShortName: String,
    override val replyMarkup: InlineKeyboardMarkup? = null,
) : InlineQueryResult {
    override val type: String = "game"
}
