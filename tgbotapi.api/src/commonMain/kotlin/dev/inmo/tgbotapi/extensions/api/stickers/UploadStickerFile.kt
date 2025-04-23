package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.UploadStickerFile
import dev.inmo.tgbotapi.types.StickerFormat
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.files.File

public suspend fun TelegramBot.uploadStickerFile(
    userId: UserId,
    sticker: MultipartFile,
    stickerFormat: StickerFormat,
): File = execute(
    UploadStickerFile(userId, sticker, stickerFormat),
)

public suspend fun TelegramBot.uploadStickerFile(
    user: CommonUser,
    sticker: MultipartFile,
    stickerFormat: StickerFormat,
): File = execute(
    UploadStickerFile(user.id, sticker, stickerFormat),
)
