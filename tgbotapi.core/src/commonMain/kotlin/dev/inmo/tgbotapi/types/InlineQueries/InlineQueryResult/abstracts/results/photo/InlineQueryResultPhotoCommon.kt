package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultPhotoType = "photo"

interface InlineQueryResultPhotoCommon :
    InlineQueryResult,
    OptionallyTitledInlineQueryResult,
    DescribedInlineQueryResult,
    TextedOutput,
    WithInputMessageContentInlineQueryResult,
    WithCustomizableCaptionInlineQueryResult
