package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

interface TitledInlineQueryResult : OptionallyTitledInlineQueryResult {
    override val title: String
}
