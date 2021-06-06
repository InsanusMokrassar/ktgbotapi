package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent

interface WithInputMessageContentInlineQueryResult : InlineQueryResult {
    val inputMessageContent: InputMessageContent?
}
