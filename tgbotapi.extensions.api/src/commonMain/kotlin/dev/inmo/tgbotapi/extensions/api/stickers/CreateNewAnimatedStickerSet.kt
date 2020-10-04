package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.CreateNewAnimatedStickerSet
import dev.inmo.tgbotapi.types.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition

suspend fun TelegramBot.createNewAnimatedStickerSet(
    userId: UserId,
    name: String,
    sticker: FileId,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = execute(
    CreateNewAnimatedStickerSet(userId, name, sticker, emojis, containsMasks, maskPosition)
)

suspend fun TelegramBot.createNewAnimatedStickerSet(
    userId: UserId,
    name: String,
    sticker: MultipartFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = execute(
    CreateNewAnimatedStickerSet(userId, name, sticker, emojis, containsMasks, maskPosition)
)


suspend fun TelegramBot.createNewAnimatedStickerSet(
    user: CommonUser,
    name: String,
    sticker: FileId,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = createNewAnimatedStickerSet(
    user.id, name, sticker, emojis, containsMasks, maskPosition
)

suspend fun TelegramBot.createNewAnimatedStickerSet(
    user: CommonUser,
    name: String,
    sticker: MultipartFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = createNewAnimatedStickerSet(
    user.id, name, sticker, emojis, containsMasks, maskPosition
)
