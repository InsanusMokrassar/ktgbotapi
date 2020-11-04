package dev.inmo.tgbotapi.types.InlineQueries.query

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery
import dev.inmo.tgbotapi.types.location.StaticLocation

data class LocationInlineQuery(
    override val id: InlineQueryIdentifier,
    override val from: User,
    override val query: String,
    override val offset: String,
    val location: StaticLocation
) : InlineQuery
