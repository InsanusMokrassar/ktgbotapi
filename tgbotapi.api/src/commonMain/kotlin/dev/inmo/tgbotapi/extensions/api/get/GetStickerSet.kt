package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetStickerSet
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.files.Sticker

suspend fun TelegramBot.getStickerSet(
    name: StickerSetName
) = execute(
    GetStickerSet(name)
)

suspend fun TelegramBot.getStickerSet(
    name: String
) = getStickerSet(
    StickerSetName(name)
)

suspend fun TelegramBot.getStickerSetOrNull(
    sticker: Sticker
) = sticker.stickerSetName ?.let {
    getStickerSet(it)
}

suspend fun TelegramBot.getStickerSetOrThrow(
    sticker: Sticker
) = getStickerSet(
    sticker.stickerSetName ?: error("Sticker must contains stickerSetName to be correctly used in getStickerSet method")
)
