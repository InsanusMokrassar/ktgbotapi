package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.get.GetStickerSet
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker

suspend fun RequestsExecutor.getStickerSet(
    name: String
) = execute(
    GetStickerSet(name)
)

suspend fun RequestsExecutor.getStickerSet(
    sticker: Sticker
) = getStickerSet(
    sticker.stickerSetName ?: error("Sticker must contains stickerSetName to be correctly used in getStickerSet method")
)
