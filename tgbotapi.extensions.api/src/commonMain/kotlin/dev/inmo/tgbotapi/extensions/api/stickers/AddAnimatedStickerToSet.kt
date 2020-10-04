package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.AddAnimatedStickerToSet
import dev.inmo.tgbotapi.types.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.addAnimatedStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    AddAnimatedStickerToSet(userId, stickerSetName, sticker, emojis, maskPosition)
)

suspend fun TelegramBot.addAnimatedStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    AddAnimatedStickerToSet(userId, stickerSetName, sticker, emojis, maskPosition)
)

suspend fun TelegramBot.addAnimatedStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addAnimatedStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addAnimatedStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addAnimatedStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addAnimatedStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addAnimatedStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addAnimatedStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addAnimatedStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addAnimatedStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addAnimatedStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun TelegramBot.addAnimatedStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addAnimatedStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)
