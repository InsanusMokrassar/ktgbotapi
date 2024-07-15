package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.DeleteStickerFromSet
import dev.inmo.tgbotapi.requests.stickers.DeleteStickerSet
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.stickers.StickerSet

public suspend fun TelegramBot.deleteStickerSet(
    name: StickerSetName
): Boolean = execute(
    DeleteStickerSet(name)
)

public suspend fun TelegramBot.deleteStickerSet(
    sticker: Sticker
): Boolean = deleteStickerSet(
    sticker.stickerSetName ?: error("Unable to take name of sticker set from sticker $sticker")
)

public suspend fun TelegramBot.deleteStickerSet(
    stickerSet: StickerSet,
): Boolean = deleteStickerSet(stickerSet.name)
