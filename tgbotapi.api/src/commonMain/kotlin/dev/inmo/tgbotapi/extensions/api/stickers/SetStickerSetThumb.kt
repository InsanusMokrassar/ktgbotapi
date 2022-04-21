package dev.inmo.tgbotapi.extensions.api.thumbs

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.SetStickerSetThumb
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSetName: String,
    thumb: FileId
) = execute(
    SetStickerSetThumb(userId, thumbSetName, thumb)
)

suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSetName: String,
    thumb: MultipartFile
) = execute(
    SetStickerSetThumb(userId, thumbSetName, thumb)
)

suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSetName: String,
    thumb: FileId
) = setStickerSetThumb(
    user.id, thumbSetName, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSetName: String,
    thumb: MultipartFile
) = setStickerSetThumb(
    user.id, thumbSetName, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSet: StickerSet,
    thumb: FileId
) = setStickerSetThumb(
    userId, thumbSet.name, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSet: StickerSet,
    thumb: MultipartFile
) = setStickerSetThumb(
    userId, thumbSet.name, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSet: StickerSet,
    thumb: FileId
) = setStickerSetThumb(
    user.id, thumbSet.name, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSet: StickerSet,
    thumb: MultipartFile
) = setStickerSetThumb(
    user.id, thumbSet.name, thumb
)

