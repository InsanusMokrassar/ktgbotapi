package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.stickers.SetStickerSetTitle
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.setStickerSetTitle(
    name: StickerSetName,
    title: String
) = execute(SetStickerSetTitle(name, title))

suspend fun TelegramBot.setStickerSetTitle(
    sticker: Sticker,
    title: String
) = setStickerSetTitle(sticker.stickerSetName ?: error("Unable to take name of sticker set from sticker $sticker"), title)

suspend fun TelegramBot.setStickerSetTitle(
    stickerSet: StickerSet,
    title: String
) = setStickerSetTitle(stickerSet.name, title)
