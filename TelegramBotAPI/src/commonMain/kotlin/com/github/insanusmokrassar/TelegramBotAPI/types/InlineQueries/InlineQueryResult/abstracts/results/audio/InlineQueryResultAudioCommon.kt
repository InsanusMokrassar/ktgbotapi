package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.audio

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.WithInputMessageContentInlineQueryResult

const val inlineQueryResultAudioType = "audio"

interface InlineQueryResultAudioCommon : InlineQueryResult,
    CaptionedOutput,
    WithInputMessageContentInlineQueryResult
