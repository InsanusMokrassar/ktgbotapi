package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId

interface WithFileIdInlineQueryResult {
    val fileId: FileId
}