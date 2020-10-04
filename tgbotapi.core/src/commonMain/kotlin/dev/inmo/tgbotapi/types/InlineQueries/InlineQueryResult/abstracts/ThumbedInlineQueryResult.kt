package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

import dev.inmo.tgbotapi.utils.MimeType

interface ThumbedInlineQueryResult : InlineQueryResult {
    val thumbUrl: String?
}

interface ThumbedWithMimeTypeInlineQueryResult : ThumbedInlineQueryResult {
    val thumbMimeType: MimeType?
}