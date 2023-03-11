package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.stickers.CreateNewStickerSet
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.types.StickerFormat
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId

suspend fun TelegramBot.createNewStickerSet(
    userId: UserId,
    name: String,
    title: String,
    stickersFormat: StickerFormat,
    stickers: List<InputSticker>,
    needsRepainting: Boolean = false
) = execute(
    CreateNewStickerSet(userId, name, title, stickersFormat, stickers, needsRepainting)
)


suspend fun TelegramBot.createNewStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    stickersFormat: StickerFormat,
    stickers: List<InputSticker>,
    needsRepainting: Boolean = false,
) = createNewStickerSet(
    user.id, name, title, stickersFormat, stickers, needsRepainting
)
