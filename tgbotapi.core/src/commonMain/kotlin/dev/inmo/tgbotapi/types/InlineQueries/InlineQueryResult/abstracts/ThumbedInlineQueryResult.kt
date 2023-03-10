package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

import dev.inmo.tgbotapi.utils.MimeType

interface ThumbedInlineQueryResult : InlineQueryResult {
    val thumbnailUrl: String?
    @Deprecated("Renamed in telegram bot api", ReplaceWith("thumbnailUrl"))
    val thumbUrl: String?
        get() = thumbnailUrl
}

interface ThumbedWithMimeTypeInlineQueryResult : ThumbedInlineQueryResult {
    val thumbnailMimeType: MimeType?
    @Deprecated("Renamed in telegram bot api", ReplaceWith("thumbnailMimeType"))
    val thumbMimeType: MimeType?
        get() = thumbnailMimeType
}
