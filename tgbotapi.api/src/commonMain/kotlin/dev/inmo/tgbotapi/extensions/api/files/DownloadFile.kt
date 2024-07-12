package dev.inmo.tgbotapi.extensions.api.files

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.DownloadFile
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.MediaContent

public suspend fun TelegramBot.downloadFile(
    filePath: String
): ByteArray = execute(
    DownloadFile(filePath)
)

public suspend fun TelegramBot.downloadFile(
    pathedFile: PathedFile
): ByteArray = downloadFile(
    pathedFile.filePath
)

public suspend fun TelegramBot.downloadFile(
    fileId: FileId
): ByteArray = downloadFile(
    getFileAdditionalInfo(fileId)
)

public suspend fun TelegramBot.downloadFile(
    file: TelegramMediaFile
): ByteArray = downloadFile(
    getFileAdditionalInfo(file)
)

public suspend fun TelegramBot.downloadFile(
    file: MediaContent
): ByteArray = downloadFile(
    getFileAdditionalInfo(file.media)
)
