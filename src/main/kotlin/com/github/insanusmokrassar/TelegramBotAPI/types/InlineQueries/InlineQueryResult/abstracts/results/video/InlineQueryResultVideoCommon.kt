package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.video

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Captioned
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.MimeTyped
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultVideoType = "video"

interface InlineQueryResultVideoCommon : InlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    Captioned,
    WithInputMessageContentInlineQueryResult
