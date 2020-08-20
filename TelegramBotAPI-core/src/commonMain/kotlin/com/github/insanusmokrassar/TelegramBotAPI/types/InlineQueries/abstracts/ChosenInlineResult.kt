package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.*

interface ChosenInlineResult {
    val resultId: InlineQueryIdentifier //chosen temporary, can be changed
    val user: User
    val inlineMessageId: InlineMessageIdentifier?
    val query: String
}