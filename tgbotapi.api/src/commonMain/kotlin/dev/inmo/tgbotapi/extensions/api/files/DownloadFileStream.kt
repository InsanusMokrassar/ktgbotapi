package dev.inmo.tgbotapi.extensions.api.files

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent

suspend fun TelegramBot.downloadFileStream(
    filePath: String
) = downloadFileStreamAllocator(filePath).invoke()

suspend fun TelegramBot.downloadFileStream(
    pathedFile: PathedFile
) = downloadFileStream(pathedFile.filePath)

suspend fun TelegramBot.downloadFileStream(
    fileId: FileId
) = downloadFileStream(getFileAdditionalInfo(fileId))

suspend fun TelegramBot.downloadFileStream(
    file: TelegramMediaFile
) = downloadFileStream(getFileAdditionalInfo(file))

suspend fun TelegramBot.downloadFileStream(
    file: MediaContent
) = downloadFileStream(getFileAdditionalInfo(file.media))
