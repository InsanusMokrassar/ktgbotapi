package dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.InlineQueryId

sealed interface ChosenInlineResult : FromUser {
    val resultId: InlineQueryId // chosen temporary, can be changed
    val inlineMessageId: InlineMessageId?
    val query: String
}
