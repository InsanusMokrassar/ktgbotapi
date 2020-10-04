package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio

import dev.inmo.tgbotapi.CommonAbstracts.Performerable
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

interface InlineQueryResultAudio : InlineQueryResultAudioCommon, UrlInlineQueryResult, DuratedInlineResultQuery, Performerable, TitledInlineQueryResult