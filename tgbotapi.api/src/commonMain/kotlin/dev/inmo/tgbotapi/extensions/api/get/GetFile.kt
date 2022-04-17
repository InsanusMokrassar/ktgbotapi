package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.get.GetFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent

suspend fun TelegramBot.getFileAdditionalInfo(
    fileId: FileId
) = execute(
    GetFile(fileId)
)

suspend fun TelegramBot.getFileAdditionalInfo(
    file: TelegramMediaFile
) = getFileAdditionalInfo(file.fileId)

suspend fun TelegramBot.getFileAdditionalInfo(
    content: MediaContent
) = getFileAdditionalInfo(content.media)
