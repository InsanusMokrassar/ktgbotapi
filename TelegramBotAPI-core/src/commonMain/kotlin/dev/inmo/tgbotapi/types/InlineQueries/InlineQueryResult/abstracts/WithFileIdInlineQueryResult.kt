package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

import dev.inmo.tgbotapi.requests.abstracts.FileId

interface WithFileIdInlineQueryResult {
    val fileId: FileId
}