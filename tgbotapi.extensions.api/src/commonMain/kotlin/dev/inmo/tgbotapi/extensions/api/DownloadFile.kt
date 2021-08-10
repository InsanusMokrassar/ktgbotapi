package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.files.downloadFile
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent

@Deprecated("Replaced", ReplaceWith("downloadFile", "dev.inmo.tgbotapi.extensions.api.files.downloadFile"))
suspend fun TelegramBot.downloadFile(
    filePath: String
): ByteArray = downloadFile(filePath)

@Deprecated("Replaced", ReplaceWith("downloadFile", "dev.inmo.tgbotapi.extensions.api.files.downloadFile"))
suspend fun TelegramBot.downloadFile(
    pathedFile: PathedFile
): ByteArray = downloadFile(pathedFile)

@Deprecated("Replaced", ReplaceWith("downloadFile", "dev.inmo.tgbotapi.extensions.api.files.downloadFile"))
suspend fun TelegramBot.downloadFile(
    fileId: FileId
): ByteArray = downloadFile(fileId)

@Deprecated("Replaced", ReplaceWith("downloadFile", "dev.inmo.tgbotapi.extensions.api.files.downloadFile"))
suspend fun TelegramBot.downloadFile(
    file: TelegramMediaFile
): ByteArray = downloadFile(file)

@Deprecated("Replaced", ReplaceWith("downloadFile", "dev.inmo.tgbotapi.extensions.api.files.downloadFile"))
suspend fun TelegramBot.downloadFile(
    file: MediaContent
): ByteArray = downloadFile(file)
