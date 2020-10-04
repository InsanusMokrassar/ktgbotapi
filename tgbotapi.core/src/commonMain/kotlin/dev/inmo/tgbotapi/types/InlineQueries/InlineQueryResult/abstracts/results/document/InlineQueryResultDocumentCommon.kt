package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultDocumentType = "document"

interface InlineQueryResultDocumentCommon : InlineQueryResult,
    TitledInlineQueryResult,
    DescribedInlineQueryResult,
    CaptionedOutput,
    WithInputMessageContentInlineQueryResult
