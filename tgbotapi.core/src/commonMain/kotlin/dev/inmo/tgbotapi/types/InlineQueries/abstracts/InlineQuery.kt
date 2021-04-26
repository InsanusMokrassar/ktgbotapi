package dev.inmo.tgbotapi.types.InlineQueries.abstracts

import dev.inmo.tgbotapi.types.InlineQueryIdentifier
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.chat.ChatType

interface InlineQuery {
    val id: InlineQueryIdentifier
    val from: User
    val query: String
    val offset: String
    val chatType: ChatType?
}