package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultMpeg4GifType = "mpeg4_gif"

interface InlineQueryResultMpeg4GifCommon : InlineQueryResult,
    OptionallyTitledInlineQueryResult,
    TextedOutput,
    WithInputMessageContentInlineQueryResult
