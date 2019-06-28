package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.photo

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultPhotoType = "photo"

interface InlineQueryResultPhotoCommon : InlineQueryResult,
    OptionallyTitledInlineQueryResult,
    DescribedInlineQueryResult,
    CaptionedOutput,
    WithInputMessageContentInlineQueryResult
