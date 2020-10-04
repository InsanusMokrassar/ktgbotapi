package dev.inmo.tgbotapi.extensions.api.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.get.GetFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.TelegramMediaFile

suspend fun TelegramBot.getFileAdditionalInfo(
    fileId: FileId
) = execute(
    GetFile(fileId)
)

suspend fun TelegramBot.getFileAdditionalInfo(
    file: TelegramMediaFile
) = getFileAdditionalInfo(file.fileId)
