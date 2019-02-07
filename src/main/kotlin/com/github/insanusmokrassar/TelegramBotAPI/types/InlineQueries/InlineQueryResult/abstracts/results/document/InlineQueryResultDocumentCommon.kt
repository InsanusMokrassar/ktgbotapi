package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.document

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Captioned
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.MimeTyped
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultDocumentType = "document"

interface InlineQueryResultDocumentCommon : InlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    Captioned,
    WithInputMessageContentInlineQueryResult
