package dev.inmo.tgbotapi.types.InlineQueries.query

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.InlineQueryId
import dev.inmo.tgbotapi.types.chat.ChatType
import dev.inmo.tgbotapi.types.chat.CommonUser

sealed interface InlineQuery : FromUser {
    val id: InlineQueryId
    val query: String
    val offset: String
    val chatType: ChatType?

    override val from: CommonUser
    override val user: CommonUser
        get() = from
}
