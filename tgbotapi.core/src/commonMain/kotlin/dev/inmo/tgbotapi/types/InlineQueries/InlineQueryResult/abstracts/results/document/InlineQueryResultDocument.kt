package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document

import dev.inmo.tgbotapi.CommonAbstracts.MimeTyped
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*

interface InlineQueryResultDocument : InlineQueryResultDocumentCommon, UrlInlineQueryResult, ThumbedInlineQueryResult, ThumbSizedInlineQueryResult, MimeTyped