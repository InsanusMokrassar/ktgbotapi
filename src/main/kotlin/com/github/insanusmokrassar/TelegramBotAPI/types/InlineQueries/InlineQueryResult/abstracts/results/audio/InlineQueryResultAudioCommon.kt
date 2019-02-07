package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.audio

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Captioned
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.MimeTyped
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*

const val inlineQueryResultAudioType = "audio"

interface InlineQueryResultAudioCommon : InlineQueryResult,
    Captioned,
    WithInputMessageContentInlineQueryResult
