package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.voice

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultVoiceType = "voice"

interface InlineQueryResultVoiceCommon : InlineQueryResult,
    CaptionedOutput,
    WithInputMessageContentInlineQueryResult,
    TitledInlineQueryResult
