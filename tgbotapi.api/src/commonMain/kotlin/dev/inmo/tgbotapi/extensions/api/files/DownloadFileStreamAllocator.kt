package dev.inmo.tgbotapi.extensions.api.files

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.DownloadFileStream
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent

suspend fun TelegramBot.downloadFileStreamAllocator(
    filePath: String
) = execute(DownloadFileStream(filePath))

suspend fun TelegramBot.downloadFileStreamAllocator(
    pathedFile: PathedFile
) = downloadFileStreamAllocator(pathedFile.filePath)

suspend fun TelegramBot.downloadFileStreamAllocator(
    fileId: FileId
) = downloadFileStreamAllocator(getFileAdditionalInfo(fileId))

suspend fun TelegramBot.downloadFileStreamAllocator(
    file: TelegramMediaFile
) = downloadFileStreamAllocator(getFileAdditionalInfo(file))

suspend fun TelegramBot.downloadFileStreamAllocator(
    file: MediaContent
) = downloadFileStreamAllocator(getFileAdditionalInfo(file.media))
