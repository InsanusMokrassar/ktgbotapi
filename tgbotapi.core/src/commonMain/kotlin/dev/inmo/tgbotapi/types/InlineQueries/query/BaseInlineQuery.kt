package dev.inmo.tgbotapi.types.InlineQueries.query

import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery
import dev.inmo.tgbotapi.types.InlineQueryIdentifier
import dev.inmo.tgbotapi.types.User

data class BaseInlineQuery(
    override val id: InlineQueryIdentifier,
    override val from: User,
    override val query: String,
    override val offset: String
) : InlineQuery
