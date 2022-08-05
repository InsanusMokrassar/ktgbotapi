package dev.inmo.tgbotapi.extensions.utils.types.files

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DownloadFileStream
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.get.GetFile
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.MediaContent
import dev.inmo.tgbotapi.utils.ByteReadChannelAllocator

suspend fun multipartFile(
    downloadStreamAllocator: ByteReadChannelAllocator,
    pathedFile: PathedFile
): MultipartFile {
    return downloadStreamAllocator.asMultipartFile(pathedFile.fileName)
}

suspend fun TelegramBot.multipartFile(
    pathedFile: PathedFile
): MultipartFile = multipartFile(
    execute(DownloadFileStream(pathedFile.filePath)),
    pathedFile
)

suspend fun TelegramBot.multipartFile(
    fileId: FileId
): MultipartFile = multipartFile(execute(GetFile(fileId)))

suspend fun TelegramBot.multipartFile(
    file: TelegramMediaFile
): MultipartFile = multipartFile(file.fileId)

suspend fun TelegramBot.multipartFile(
    content: MediaContent
): MultipartFile = multipartFile(content.media)
