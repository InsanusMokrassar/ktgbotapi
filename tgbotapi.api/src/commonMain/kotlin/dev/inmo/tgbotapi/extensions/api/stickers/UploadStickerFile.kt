package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.UploadStickerFile
import dev.inmo.tgbotapi.types.CommonUser
import dev.inmo.tgbotapi.types.UserId

suspend fun TelegramBot.uploadStickerFile(
    userId: UserId,
    sticker: MultipartFile
) = execute(
    UploadStickerFile(userId, sticker)
)

suspend fun TelegramBot.uploadStickerFile(
    user: CommonUser,
    sticker: MultipartFile
) = execute(
    UploadStickerFile(user.id, sticker)
)
