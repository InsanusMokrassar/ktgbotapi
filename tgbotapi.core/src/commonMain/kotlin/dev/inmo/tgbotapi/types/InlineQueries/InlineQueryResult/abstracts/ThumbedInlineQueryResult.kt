package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

import dev.inmo.tgbotapi.utils.MimeType

interface ThumbedInlineQueryResult : InlineQueryResult {
    val thumbnailUrl: String?
}

interface ThumbedWithMimeTypeInlineQueryResult : ThumbedInlineQueryResult {
    val thumbnailMimeType: MimeType?
}
