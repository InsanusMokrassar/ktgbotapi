package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

interface ThumbSizedInlineQueryResult : InlineQueryResult, ThumbedInlineQueryResult {
    val thumbWidth: Int?
    val thumbHeight: Int?
}