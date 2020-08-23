package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.query

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InlineQuery

data class LocationInlineQuery(
    override val id: InlineQueryIdentifier,
    override val from: User,
    override val query: String,
    override val offset: String,
    val location: Location
) : InlineQuery
