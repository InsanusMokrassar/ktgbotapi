package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

interface ThumbSizedInlineQueryResult : InlineQueryResult, ThumbedInlineQueryResult {
    val thumbnailWidth: Int?
    @Deprecated("Renamed in telegram bot api", ReplaceWith("thumbnailWidth"))
    val thumbWidth: Int?
        get() = thumbnailWidth
    val thumbnailHeight: Int?
    @Deprecated("Renamed in telegram bot api", ReplaceWith("thumbnailHeight"))
    val thumbHeight: Int?
        get() = thumbnailHeight
}
