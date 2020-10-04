package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

interface SizedInlineQueryResult : InlineQueryResult {
    val width: Int?
    val height: Int?
}
