package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.UploadStickerFile
import dev.inmo.tgbotapi.types.StickerFormat
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId

suspend fun TelegramBot.uploadStickerFile(
    userId: UserId,
    sticker: MultipartFile,
    stickerFormat: StickerFormat
) = execute(
    UploadStickerFile(userId, sticker, stickerFormat)
)

suspend fun TelegramBot.uploadStickerFile(
    user: CommonUser,
    sticker: MultipartFile,
    stickerFormat: StickerFormat
) = execute(
    UploadStickerFile(user.id, sticker, stickerFormat)
)
