package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.gif

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Captioned
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultGifType = "gif"

interface InlineQueryResultGifCommon : InlineQueryResult,
    OptionallyTitledInlineQueryResult,
    Captioned,
    WithInputMessageContentInlineQueryResult
