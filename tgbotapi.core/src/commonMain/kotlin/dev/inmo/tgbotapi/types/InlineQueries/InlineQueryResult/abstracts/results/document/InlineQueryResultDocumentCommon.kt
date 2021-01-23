package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document

import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultDocumentType = "document"

interface InlineQueryResultDocumentCommon : InlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    TextedOutput,
    WithInputMessageContentInlineQueryResult
