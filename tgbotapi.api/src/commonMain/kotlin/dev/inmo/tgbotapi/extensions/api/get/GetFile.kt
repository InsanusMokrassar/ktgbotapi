package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.get.GetFile
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.MediaContent

public suspend fun TelegramBot.getFileAdditionalInfo(fileId: FileId): PathedFile =
    execute(
        GetFile(fileId),
    )

public suspend fun TelegramBot.getFileAdditionalInfo(file: TelegramMediaFile): PathedFile = getFileAdditionalInfo(file.fileId)

public suspend fun TelegramBot.getFileAdditionalInfo(content: MediaContent): PathedFile = getFileAdditionalInfo(content.media)
