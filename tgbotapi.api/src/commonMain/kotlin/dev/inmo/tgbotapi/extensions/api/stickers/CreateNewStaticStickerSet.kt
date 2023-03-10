package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.CreateNewStickerSet
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.types.StickerFormat
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition

suspend fun TelegramBot.createNewStickerSet(
    userId: UserId,
    name: String,
    title: String,
    stickersFormat: StickerFormat,
    stickers: List<InputSticker>,
) = execute(
    CreateNewStickerSet(userId, name, title, stickersFormat, stickers)
)


suspend fun TelegramBot.createNewStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    stickersFormat: StickerFormat,
    stickers: List<InputSticker>,
) = createNewStickerSet(
    user.id, name, title, stickersFormat, stickers
)
