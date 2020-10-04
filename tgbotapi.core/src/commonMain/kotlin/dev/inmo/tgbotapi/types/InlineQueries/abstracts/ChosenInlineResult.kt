package dev.inmo.tgbotapi.types.InlineQueries.abstracts

import dev.inmo.tgbotapi.types.*

interface ChosenInlineResult {
    val resultId: InlineQueryIdentifier //chosen temporary, can be changed
    val user: User
    val inlineMessageId: InlineMessageIdentifier?
    val query: String
}