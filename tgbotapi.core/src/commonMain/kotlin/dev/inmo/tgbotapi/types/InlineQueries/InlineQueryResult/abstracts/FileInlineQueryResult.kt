package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts

import dev.inmo.tgbotapi.requests.abstracts.FileId

interface FileInlineQueryResult : InlineQueryResult {
    val fileId: FileId
}
