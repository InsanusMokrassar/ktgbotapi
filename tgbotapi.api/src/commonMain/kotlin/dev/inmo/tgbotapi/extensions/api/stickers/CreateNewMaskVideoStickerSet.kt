package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.*
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition

suspend fun TelegramBot.createNewMaskVideoStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition
) = execute(
    CreateNewMaskVideoStickerSet(userId, name, title, sticker, emojis, maskPosition)
)

suspend fun TelegramBot.createNewMaskVideoStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition
) = execute(
    CreateNewMaskVideoStickerSet(userId, name, title, sticker, emojis, maskPosition)
)


suspend fun TelegramBot.createNewMaskVideoStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition
) = createNewMaskVideoStickerSet(
    user.id, name, title, sticker, emojis, maskPosition
)

suspend fun TelegramBot.createNewMaskVideoStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition
) = createNewMaskVideoStickerSet(
    user.id, name, title, sticker, emojis, maskPosition
)
