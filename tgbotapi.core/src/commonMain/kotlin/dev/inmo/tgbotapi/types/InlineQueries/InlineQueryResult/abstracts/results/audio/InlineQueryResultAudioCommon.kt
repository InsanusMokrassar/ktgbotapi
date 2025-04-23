package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.WithInputMessageContentInlineQueryResult

const val inlineQueryResultAudioType = "audio"

interface InlineQueryResultAudioCommon :
    InlineQueryResult,
    TextedOutput,
    WithInputMessageContentInlineQueryResult
