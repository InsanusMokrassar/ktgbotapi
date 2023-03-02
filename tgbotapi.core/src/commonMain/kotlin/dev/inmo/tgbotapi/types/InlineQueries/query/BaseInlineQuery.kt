package dev.inmo.tgbotapi.types.InlineQueries.query

import dev.inmo.tgbotapi.types.InlineQueryIdentifier
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.ChatType
import dev.inmo.tgbotapi.types.chat.CommonUser

data class BaseInlineQuery(
    override val id: InlineQueryIdentifier,
    override val from: CommonUser,
    override val query: String,
    override val offset: String,
    override val chatType: ChatType?
) : InlineQuery
