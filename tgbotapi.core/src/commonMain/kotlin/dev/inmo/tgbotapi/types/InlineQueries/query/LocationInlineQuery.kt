package dev.inmo.tgbotapi.types.InlineQueries.query

import dev.inmo.tgbotapi.types.InlineQueryIdentifier
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.ChatType
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.location.Location

data class LocationInlineQuery(
    override val id: InlineQueryIdentifier,
    override val from: CommonUser,
    override val query: String,
    override val offset: String,
    override val chatType: ChatType?,
    val location: Location
) : InlineQuery
