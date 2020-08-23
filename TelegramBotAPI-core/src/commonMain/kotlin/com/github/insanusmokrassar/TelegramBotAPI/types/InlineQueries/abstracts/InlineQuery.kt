package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueryIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.User

interface InlineQuery {
    val id: InlineQueryIdentifier
    val from: User
    val query: String
    val offset: String
}