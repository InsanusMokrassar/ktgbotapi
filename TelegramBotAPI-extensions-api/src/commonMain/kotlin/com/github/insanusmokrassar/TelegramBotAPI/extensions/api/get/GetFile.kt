package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.get.GetFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.TelegramMediaFile

suspend fun RequestsExecutor.getFileAdditionalInfo(
    fileId: FileId
) = execute(
    GetFile(fileId)
)

suspend fun RequestsExecutor.getFileAdditionalInfo(
    file: TelegramMediaFile
) = getFileAdditionalInfo(file.fileId)
