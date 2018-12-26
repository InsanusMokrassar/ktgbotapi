package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

interface TitledInlineQueryResult : OptionallyTitledInlineQueryResult {
    override val title: String
}
