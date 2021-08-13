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
    pathedFile: PathedFile,
    mimeType: MimeType
): StorageFile {
    return downloadStreamAllocator.asStorageFile(
        pathedFile.fileName,
        mimeType
    )
}

suspend fun TelegramBot.convertToStorageFile(
    pathedFile: PathedFile,
    mimeType: MimeType
): StorageFile = convertToStorageFile(
    execute(DownloadFileStream(pathedFile.filePath)),
    pathedFile,
    mimeType
)

suspend fun TelegramBot.convertToStorageFile(
    fileId: FileId,
    mimeType: MimeType
): StorageFile = convertToStorageFile(execute(GetFile(fileId)), mimeType)

suspend fun TelegramBot.convertToStorageFile(
    file: TelegramMediaFile,
    mimeType: MimeType
): StorageFile = convertToStorageFile(file.fileId, mimeType)

suspend fun TelegramBot.convertToStorageFile(
    content: MediaContent,
    mimeType: MimeType
): StorageFile = convertToStorageFile(content.media, mimeType)
