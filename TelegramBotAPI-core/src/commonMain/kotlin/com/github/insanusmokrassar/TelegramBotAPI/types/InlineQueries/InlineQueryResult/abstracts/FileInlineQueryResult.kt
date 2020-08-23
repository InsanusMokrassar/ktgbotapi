package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId

interface FileInlineQueryResult: InlineQueryResult {
    val fileId: FileId
}
