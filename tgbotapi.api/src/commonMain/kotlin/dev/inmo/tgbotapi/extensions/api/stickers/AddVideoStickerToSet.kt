package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.AddVideoStickerToSet
import dev.inmo.tgbotapi.types.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.addVideoStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    AddVideoStickerToSet(userId, stickerSetName, sticker, emojis, maskPosition)
)

suspend fun TelegramBot.addVideoStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    AddVideoStickerToSet(userId, stickerSetName, sticker, emojis, maskPosition)
)

suspend fun TelegramBot.addVideoStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addVideoStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addVideoStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addVideoStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addVideoStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addVideoStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addVideoStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addVideoStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addVideoStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addVideoStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addVideoStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addVideoStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)
