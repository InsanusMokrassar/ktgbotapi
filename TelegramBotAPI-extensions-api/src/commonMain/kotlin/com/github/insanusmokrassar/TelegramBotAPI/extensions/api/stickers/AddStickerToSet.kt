package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.AddStickerToSet
import com.github.insanusmokrassar.TelegramBotAPI.types.CommonUser
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.StickerSet

suspend fun RequestsExecutor.addStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    AddStickerToSet(userId, stickerSetName, sticker, emojis, maskPosition)
)

suspend fun RequestsExecutor.addStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    AddStickerToSet(userId, stickerSetName, sticker, emojis, maskPosition)
)

suspend fun RequestsExecutor.addStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)
