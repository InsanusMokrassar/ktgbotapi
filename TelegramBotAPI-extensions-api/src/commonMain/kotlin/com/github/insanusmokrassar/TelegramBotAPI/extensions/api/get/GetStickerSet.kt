package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.get.GetStickerSet
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker

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
