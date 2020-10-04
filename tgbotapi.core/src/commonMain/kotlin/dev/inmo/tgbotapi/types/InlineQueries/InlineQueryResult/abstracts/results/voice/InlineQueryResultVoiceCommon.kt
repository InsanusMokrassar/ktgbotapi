package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultVoiceType = "voice"

interface InlineQueryResultVoiceCommon : InlineQueryResult,
    CaptionedOutput,
    WithInputMessageContentInlineQueryResult,
    TitledInlineQueryResult
