package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

interface ThumbSizedInlineQueryResult : InlineQueryResult, ThumbedInlineQueryResult {
    val thumbWidth: Int?
    val thumbHeight: Int?
}