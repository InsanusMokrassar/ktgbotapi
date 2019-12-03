package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.video

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultVideoType = "video"

interface InlineQueryResultVideoCommon : InlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    CaptionedOutput,
    WithInputMessageContentInlineQueryResult
