package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent

interface WithInputMessageContentInlineQueryResult : InlineQueryResult {
    val inputMessageContent: InputMessageContent?
}
