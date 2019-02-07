package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.gif

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Captioned
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

interface InlineQueryResultGifCommon : InlineQueryResult,
    OptionallyTitledInlineQueryResult,
    Captioned,
    WithInputMessageContentInlineQueryResult
{
    override val type: String
        get() = "gif"
}