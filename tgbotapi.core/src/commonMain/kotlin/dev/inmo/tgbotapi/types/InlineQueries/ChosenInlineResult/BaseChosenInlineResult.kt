package dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult

import dev.inmo.tgbotapi.types.*

data class BaseChosenInlineResult(
    override val resultId: InlineQueryIdentifier,
    override val user: User,
    override val inlineMessageId: InlineMessageIdentifier?,
    override val query: String
) : ChosenInlineResult
