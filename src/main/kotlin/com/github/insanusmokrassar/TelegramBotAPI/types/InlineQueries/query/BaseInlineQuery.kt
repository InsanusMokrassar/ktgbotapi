package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.query

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InlineQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueryIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.User

data class BaseInlineQuery(
    override val id: InlineQueryIdentifier,
    override val from: User,
    override val query: String,
    override val offset: String
) : InlineQuery
