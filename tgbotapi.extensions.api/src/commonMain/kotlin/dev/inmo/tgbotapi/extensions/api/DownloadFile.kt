package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.DownloadFile
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent

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

suspend fun TelegramBot.downloadFile(
    file: MediaContent
): ByteArray = downloadFile(
    getFileAdditionalInfo(file.media)
)
