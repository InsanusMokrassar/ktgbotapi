package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

interface DescribedInlineQueryResult : InlineQueryResult {
    val description: String?
}
