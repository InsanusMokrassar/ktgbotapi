package dev.inmo.tgbotapi.extensions.api.thumbs

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.SetStickerSetThumbnail
import dev.inmo.tgbotapi.types.StickerFormat
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    format: StickerFormat,
    thumbnail: FileId
) = execute(
    SetStickerSetThumbnail(userId, stickerSetName, format, thumbnail)
)

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    format: StickerFormat,
    thumbnail: MultipartFile
) = execute(
    SetStickerSetThumbnail(userId, stickerSetName, format, thumbnail)
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSetName: StickerSetName,
    format: StickerFormat,
    thumbnail: FileId
) = setStickerSetThumbnail(
    user.id, stickerSetName, format, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSetName: StickerSetName,
    format: StickerFormat,
    thumbnail: MultipartFile
) = setStickerSetThumbnail(
    user.id, stickerSetName, format, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSet: StickerSet,
    format: StickerFormat,
    thumbnail: FileId
) = setStickerSetThumbnail(
    userId, stickerSet.name, format, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSet: StickerSet,
    format: StickerFormat,
    thumbnail: MultipartFile
) = setStickerSetThumbnail(
    userId, stickerSet.name, format, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSet: StickerSet,
    format: StickerFormat,
    thumbnail: FileId
) = setStickerSetThumbnail(
    user.id, stickerSet, format, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSet: StickerSet,
    format: StickerFormat,
    thumbnail: MultipartFile
) = setStickerSetThumbnail(
    user.id, stickerSet, format, thumbnail
)
