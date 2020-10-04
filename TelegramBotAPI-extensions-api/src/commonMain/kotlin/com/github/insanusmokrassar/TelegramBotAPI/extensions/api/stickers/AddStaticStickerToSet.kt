package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.AddStaticStickerToSet
import com.github.insanusmokrassar.TelegramBotAPI.types.CommonUser
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.StickerSet

suspend fun TelegramBot.addStaticStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    AddStaticStickerToSet(userId, stickerSetName, sticker, emojis, maskPosition)
)

suspend fun TelegramBot.addStaticStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    AddStaticStickerToSet(userId, stickerSetName, sticker, emojis, maskPosition)
)

suspend fun TelegramBot.addStaticStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStaticStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addStaticStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStaticStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addStaticStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStaticStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addStaticStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStaticStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addStaticStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStaticStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addStaticStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStaticStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)
