package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultGifType = "gif"

interface InlineQueryResultGifCommon :
    InlineQueryResult,
    OptionallyTitledInlineQueryResult,
    TextedOutput,
    WithInputMessageContentInlineQueryResult,
    WithCustomizableCaptionInlineQueryResult
