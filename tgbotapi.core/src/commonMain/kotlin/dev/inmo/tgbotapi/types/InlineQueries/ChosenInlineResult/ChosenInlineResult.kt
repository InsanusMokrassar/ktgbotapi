package dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult

import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.types.*

sealed interface ChosenInlineResult : FromUser {
    val resultId: InlineQueryIdentifier //chosen temporary, can be changed
    val inlineMessageId: InlineMessageIdentifier?
    val query: String
}
