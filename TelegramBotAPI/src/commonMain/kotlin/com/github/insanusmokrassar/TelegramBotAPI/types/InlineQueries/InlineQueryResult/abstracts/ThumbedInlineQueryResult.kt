package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.utils.MimeType

interface ThumbedInlineQueryResult : InlineQueryResult {
    val thumbUrl: String?
}

interface ThumbedWithMimeTypeInlineQueryResult : ThumbedInlineQueryResult {
    val thumbMimeType: MimeType?
}