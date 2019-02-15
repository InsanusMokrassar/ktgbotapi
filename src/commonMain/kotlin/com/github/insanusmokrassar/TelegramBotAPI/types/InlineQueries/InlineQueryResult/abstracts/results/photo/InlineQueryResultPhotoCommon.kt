package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.photo

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Captioned
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultPhotoType = "photo"

interface InlineQueryResultPhotoCommon : InlineQueryResult,
    OptionallyTitledInlineQueryResult,
    DescribedInlineQueryResult,
    Captioned,
    WithInputMessageContentInlineQueryResult
