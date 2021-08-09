package dev.inmo.tgbotapi.extensions.api

import dev.inmo.micro_utils.coroutines.doOutsideOfCoroutine
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.DownloadFile
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent
import java.io.File

suspend fun TelegramBot.downloadFile(
    filePath: String,
    destFile: File
): File {
    val bytes = downloadFile(filePath)
    destFile.deleteRecursively()

    doOutsideOfCoroutine { destFile.createNewFile() }
    destFile.writeBytes(bytes)

    return destFile
}

suspend fun TelegramBot.downloadFile(
    pathedFile: PathedFile,
    destFile: File
) = downloadFile(
    pathedFile.filePath,
    destFile
)

suspend fun TelegramBot.downloadFile(
    fileId: FileId,
    destFile: File
) = downloadFile(
    getFileAdditionalInfo(fileId),
    destFile
)

suspend fun TelegramBot.downloadFile(
    file: TelegramMediaFile,
    destFile: File
): File = downloadFile(
    getFileAdditionalInfo(file),
    destFile
)

suspend fun TelegramBot.downloadFile(
    file: MediaContent,
    destFile: File
) = downloadFile(
    getFileAdditionalInfo(file.media),
    destFile
)
