package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.gif

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultGifType = "gif"

interface InlineQueryResultGifCommon : InlineQueryResult,
    OptionallyTitledInlineQueryResult,
    CaptionedOutput,
    WithInputMessageContentInlineQueryResult
