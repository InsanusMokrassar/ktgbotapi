package dev.inmo.tgbotapi.types.InlineQueries.query

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.InlineQueryIdentifier
import dev.inmo.tgbotapi.types.chat.ChatType

sealed interface InlineQuery : FromUser {
    val id: InlineQueryIdentifier
    val query: String
    val offset: String
    val chatType: ChatType?
}
