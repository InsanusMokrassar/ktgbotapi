package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.CreateNewStaticStickerSet
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition

suspend fun TelegramBot.createNewStaticStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: FileId,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = execute(
    CreateNewStaticStickerSet(userId, name, title, sticker, emojis, containsMasks, maskPosition)
)

suspend fun TelegramBot.createNewStaticStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: MultipartFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = execute(
    CreateNewStaticStickerSet(userId, name, title, sticker, emojis, containsMasks, maskPosition)
)


suspend fun TelegramBot.createNewStaticStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    sticker: FileId,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = createNewStaticStickerSet(
    user.id, name, title, sticker, emojis, containsMasks, maskPosition
)

suspend fun TelegramBot.createNewStaticStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    sticker: MultipartFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = createNewStaticStickerSet(
    user.id, name, title, sticker, emojis, containsMasks, maskPosition
)
