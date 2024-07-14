package dev.inmo.tgbotapi.extensions.api.files

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.MediaContent
import io.ktor.utils.io.*

public suspend fun TelegramBot.downloadFileStream(
    filePath: String
): ByteReadChannel = downloadFileStreamAllocator(filePath).invoke()

public suspend fun TelegramBot.downloadFileStream(
    pathedFile: PathedFile
): ByteReadChannel = downloadFileStream(pathedFile.filePath)

public suspend fun TelegramBot.downloadFileStream(
    fileId: FileId
): ByteReadChannel = downloadFileStream(getFileAdditionalInfo(fileId))

public suspend fun TelegramBot.downloadFileStream(
    file: TelegramMediaFile
): ByteReadChannel = downloadFileStream(getFileAdditionalInfo(file))

public suspend fun TelegramBot.downloadFileStream(
    file: MediaContent
): ByteReadChannel = downloadFileStream(getFileAdditionalInfo(file.media))
