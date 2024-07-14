package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.stickers.CreateNewStickerSet
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.types.StickerFormat
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId

public suspend fun TelegramBot.createNewStickerSet(
    userId: UserId,
    name: String,
    title: String,
    stickers: List<InputSticker>,
    needsRepainting: Boolean = false
): Boolean = execute(
    CreateNewStickerSet(userId, name, title, stickers, needsRepainting)
)


public suspend fun TelegramBot.createNewStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    stickers: List<InputSticker>,
    needsRepainting: Boolean = false,
): Boolean = createNewStickerSet(
    user.id, name, title, stickers, needsRepainting
)
