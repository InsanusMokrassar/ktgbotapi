package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

interface SizedInlineQueryResult : InlineQueryResult {
    val width: Int?
    val height: Int?
}
