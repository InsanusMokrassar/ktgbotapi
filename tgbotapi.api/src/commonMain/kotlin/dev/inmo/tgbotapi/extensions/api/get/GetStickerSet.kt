package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetStickerSet
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.stickers.StickerSet

public suspend fun TelegramBot.getStickerSet(name: StickerSetName): StickerSet = execute(
    GetStickerSet(name),
)

public suspend fun TelegramBot.getStickerSet(name: String): StickerSet = getStickerSet(
    StickerSetName(name),
)

public suspend fun TelegramBot.getStickerSetOrNull(sticker: Sticker): StickerSet? = sticker.stickerSetName ?.let {
    getStickerSet(it)
}

public suspend fun TelegramBot.getStickerSetOrThrow(sticker: Sticker): StickerSet = getStickerSet(
    sticker.stickerSetName ?: error("Sticker must contains stickerSetName to be correctly used in getStickerSet method"),
)
