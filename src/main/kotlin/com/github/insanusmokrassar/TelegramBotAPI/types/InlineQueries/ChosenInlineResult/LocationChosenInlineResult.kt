package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.ChosenInlineResult

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.ChosenInlineResult

data class LocationChosenInlineResult(
    override val resultId: InlineQueryIdentifier,
    override val user: User,
    val location: Location,
    override val inlineMessageId: InlineMessageIdentifier?,
    override val query: String
) : ChosenInlineResult
