package dev.inmo.tgbotapi.types.InlineQueries.query

import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery
import dev.inmo.tgbotapi.types.InlineQueryIdentifier
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.chat.ChatType
import dev.inmo.tgbotapi.types.location.Location
import kotlinx.serialization.Serializable

data class LocationInlineQuery(
    override val id: InlineQueryIdentifier,
    override val from: User,
    override val query: String,
    override val offset: String,
    override val chatType: ChatType?,
    val location: Location
) : InlineQuery
