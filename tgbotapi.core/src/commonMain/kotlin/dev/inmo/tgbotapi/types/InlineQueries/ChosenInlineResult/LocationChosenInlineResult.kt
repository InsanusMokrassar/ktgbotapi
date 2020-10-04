package dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.ChosenInlineResult

data class LocationChosenInlineResult(
    override val resultId: InlineQueryIdentifier,
    override val user: User,
    val location: Location,
    override val inlineMessageId: InlineMessageIdentifier?,
    override val query: String
) : ChosenInlineResult
