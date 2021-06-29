package dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult

import dev.inmo.tgbotapi.types.*

sealed interface ChosenInlineResult {
    val resultId: InlineQueryIdentifier //chosen temporary, can be changed
    val user: User
    val inlineMessageId: InlineMessageIdentifier?
    val query: String
}
