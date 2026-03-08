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

public suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    format: StickerFormat,
    thumbnail: FileId
): Unit = execute(
    SetStickerSetThumbnail(userId, stickerSetName, format, thumbnail)
)

public suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    format: StickerFormat,
    thumbnail: MultipartFile
): Unit = execute(
    SetStickerSetThumbnail(userId, stickerSetName, format, thumbnail)
)

public suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSetName: StickerSetName,
    format: StickerFormat,
    thumbnail: FileId
): Unit = setStickerSetThumbnail(
    user.id, stickerSetName, format, thumbnail
)

public suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSetName: StickerSetName,
    format: StickerFormat,
    thumbnail: MultipartFile
): Unit = setStickerSetThumbnail(
    user.id, stickerSetName, format, thumbnail
)

public suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSet: StickerSet,
    format: StickerFormat,
    thumbnail: FileId
): Unit = setStickerSetThumbnail(
    userId, stickerSet.name, format, thumbnail
)

public suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSet: StickerSet,
    format: StickerFormat,
    thumbnail: MultipartFile
): Unit = setStickerSetThumbnail(
    userId, stickerSet.name, format, thumbnail
)

public suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSet: StickerSet,
    format: StickerFormat,
    thumbnail: FileId
): Unit = setStickerSetThumbnail(
    user.id, stickerSet, format, thumbnail
)

public suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSet: StickerSet,
    format: StickerFormat,
    thumbnail: MultipartFile
): Unit = setStickerSetThumbnail(
    user.id, stickerSet, format, thumbnail
)
