package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

interface DescribedInlineQueryResult : InlineQueryResult {
    val description: String?
}