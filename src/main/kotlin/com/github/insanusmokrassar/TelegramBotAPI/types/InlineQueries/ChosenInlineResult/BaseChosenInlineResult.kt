package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.ChosenInlineResult

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.ChosenInlineResult

data class BaseChosenInlineResult(
    override val resultId: InlineQueryIdentifier,
    override val user: User,
    override val inlineMessageId: InlineMessageIdentifier?,
    override val query: String
) : ChosenInlineResult
