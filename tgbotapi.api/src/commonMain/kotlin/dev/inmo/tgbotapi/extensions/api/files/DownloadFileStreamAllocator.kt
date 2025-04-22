package dev.inmo.tgbotapi.extensions.api.files

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.DownloadFileStream
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.MediaContent
import dev.inmo.tgbotapi.utils.ByteReadChannelAllocator

public suspend fun TelegramBot.downloadFileStreamAllocator(filePath: String): ByteReadChannelAllocator =
    execute(DownloadFileStream(filePath))

public suspend fun TelegramBot.downloadFileStreamAllocator(pathedFile: PathedFile): ByteReadChannelAllocator =
    downloadFileStreamAllocator(pathedFile.filePath)

public suspend fun TelegramBot.downloadFileStreamAllocator(fileId: FileId): ByteReadChannelAllocator =
    downloadFileStreamAllocator(getFileAdditionalInfo(fileId))

public suspend fun TelegramBot.downloadFileStreamAllocator(file: TelegramMediaFile): ByteReadChannelAllocator =
    downloadFileStreamAllocator(getFileAdditionalInfo(file))

public suspend fun TelegramBot.downloadFileStreamAllocator(file: MediaContent): ByteReadChannelAllocator =
    downloadFileStreamAllocator(getFileAdditionalInfo(file.media))
