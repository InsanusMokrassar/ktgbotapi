package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video

import dev.inmo.tgbotapi.abstracts.MimeTyped
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

interface InlineQueryResultVideo :
    InlineQueryResultVideoCommon,
    UrlInlineQueryResult,
    ThumbedInlineQueryResult,
    MimeTyped,
    SizedInlineQueryResult,
    DuratedInlineResultQuery
