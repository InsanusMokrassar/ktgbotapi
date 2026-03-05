package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.stickers.SetStickerSetTitle
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.stickers.StickerSet

public suspend fun TelegramBot.setStickerSetTitle(
    name: StickerSetName,
    title: String
): Unit = execute(SetStickerSetTitle(name, title))

public suspend fun TelegramBot.setStickerSetTitle(
    sticker: Sticker,
    title: String
): Unit = setStickerSetTitle(sticker.stickerSetName ?: error("Unable to take name of sticker set from sticker $sticker"), title)

public suspend fun TelegramBot.setStickerSetTitle(
    stickerSet: StickerSet,
    title: String
): Unit = setStickerSetTitle(stickerSet.name, title)
