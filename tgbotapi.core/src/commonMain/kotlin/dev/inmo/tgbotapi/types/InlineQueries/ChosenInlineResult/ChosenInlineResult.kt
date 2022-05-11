package dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.InlineMessageIdentifier
import dev.inmo.tgbotapi.types.InlineQueryIdentifier

sealed interface ChosenInlineResult : FromUser {
    val resultId: InlineQueryIdentifier //chosen temporary, can be changed
    val inlineMessageId: InlineMessageIdentifier?
    val query: String
}
