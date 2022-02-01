package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetStickerSet
import dev.inmo.tgbotapi.types.files.sticker.Sticker

suspend fun TelegramBot.getStickerSet(
    name: String
) = execute(
    GetStickerSet(name)
)

suspend fun TelegramBot.getStickerSet(
    sticker: Sticker
) = getStickerSet(
    sticker.stickerSetName ?: error("Sticker must contains stickerSetName to be correctly used in getStickerSet method")
)
