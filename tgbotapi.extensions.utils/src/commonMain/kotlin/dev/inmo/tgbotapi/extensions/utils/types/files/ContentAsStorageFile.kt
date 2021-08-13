package dev.inmo.tgbotapi.extensions.utils.types.files

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DownloadFileStream
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.get.GetFile
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent
import dev.inmo.tgbotapi.utils.*

suspend fun convertToStorageFile(
    downloadStreamAllocator: ByteReadChannelAllocator,
    pathedFile: PathedFile
): StorageFile {
    return downloadStreamAllocator.asStorageFile(
        pathedFile.fileName
    )
}

suspend fun TelegramBot.convertToStorageFile(
    pathedFile: PathedFile
): StorageFile = convertToStorageFile(
    execute(DownloadFileStream(pathedFile.filePath)),
    pathedFile
)

suspend fun TelegramBot.convertToStorageFile(
    fileId: FileId
): StorageFile = convertToStorageFile(execute(GetFile(fileId)))

suspend fun TelegramBot.convertToStorageFile(
    file: TelegramMediaFile
): StorageFile = convertToStorageFile(file.fileId)

suspend fun TelegramBot.convertToStorageFile(
    content: MediaContent
): StorageFile = convertToStorageFile(content.media)
