package dev.inmo.tgbotapi.extensions.api.thumbs

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.SetStickerSetThumbnail
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    thumbnail: FileId
) = execute(
    SetStickerSetThumbnail(userId, stickerSetName, thumbnail)
)

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    thumbnail: MultipartFile
) = execute(
    SetStickerSetThumbnail(userId, stickerSetName, thumbnail)
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSetName: StickerSetName,
    thumbnail: FileId
) = setStickerSetThumbnail(
    user.id, stickerSetName, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSetName: StickerSetName,
    thumbnail: MultipartFile
) = setStickerSetThumbnail(
    user.id, stickerSetName, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSet: StickerSet,
    thumbnail: FileId
) = setStickerSetThumbnail(
    userId, stickerSet.name, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSet: StickerSet,
    thumbnail: MultipartFile
) = setStickerSetThumbnail(
    userId, stickerSet.name, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSet: StickerSet,
    thumbnail: FileId
) = setStickerSetThumbnail(
    user.id, stickerSet, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSet: StickerSet,
    thumbnail: MultipartFile
) = setStickerSetThumbnail(
    user.id, stickerSet, thumbnail
)
