package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

interface ThumbSizedInlineQueryResult : InlineQueryResult, ThumbedInlineQueryResult {
    val thumbnailWidth: Int?
    val thumbnailHeight: Int?
}
