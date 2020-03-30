package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.SetStickerSetThumb
import com.github.insanusmokrassar.TelegramBotAPI.types.CommonUser
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.StickerSet

suspend fun RequestsExecutor.setStickerSetThumb(
    userId: UserId,
    stickerSetName: String,
    sticker: FileId
) = execute(
    SetStickerSetThumb(userId, stickerSetName, sticker)
)

suspend fun RequestsExecutor.setStickerSetThumb(
    userId: UserId,
    stickerSetName: String,
    sticker: MultipartFile
) = execute(
    SetStickerSetThumb(userId, stickerSetName, sticker)
)

suspend fun RequestsExecutor.setStickerSetThumb(
    user: CommonUser,
    stickerSetName: String,
    sticker: FileId
) = setStickerSetThumb(
    user.id, stickerSetName, sticker
)

suspend fun RequestsExecutor.setStickerSetThumb(
    user: CommonUser,
    stickerSetName: String,
    sticker: MultipartFile
) = setStickerSetThumb(
    user.id, stickerSetName, sticker
)

suspend fun RequestsExecutor.setStickerSetThumb(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: FileId
) = setStickerSetThumb(
    userId, stickerSet.name, sticker
)

suspend fun RequestsExecutor.setStickerSetThumb(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: MultipartFile
) = setStickerSetThumb(
    userId, stickerSet.name, sticker
)

suspend fun RequestsExecutor.setStickerSetThumb(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: FileId
) = setStickerSetThumb(
    user.id, stickerSet.name, sticker
)

suspend fun RequestsExecutor.setStickerSetThumb(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: MultipartFile
) = setStickerSetThumb(
    user.id, stickerSet.name, sticker
)

