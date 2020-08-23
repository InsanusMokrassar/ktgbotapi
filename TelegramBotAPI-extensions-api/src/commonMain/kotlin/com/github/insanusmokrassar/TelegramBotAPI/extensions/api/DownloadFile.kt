package com.github.insanusmokrassar.TelegramBotAPI.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.get.getFileAdditionalInfo
import com.github.insanusmokrassar.TelegramBotAPI.requests.DownloadFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PathedFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.TelegramMediaFile

suspend fun TelegramBot.downloadFile(
    filePath: String
): ByteArray = execute(
    DownloadFile(filePath)
)

suspend fun TelegramBot.downloadFile(
    pathedFile: PathedFile
): ByteArray = execute(
    DownloadFile(pathedFile.filePath)
)

suspend fun TelegramBot.downloadFile(
    fileId: FileId
): ByteArray = downloadFile(
    getFileAdditionalInfo(fileId)
)

suspend fun TelegramBot.downloadFile(
    file: TelegramMediaFile
): ByteArray = downloadFile(
    getFileAdditionalInfo(file)
)
