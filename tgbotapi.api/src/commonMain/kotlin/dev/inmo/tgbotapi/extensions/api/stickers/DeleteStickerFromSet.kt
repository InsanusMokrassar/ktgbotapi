package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.DeleteStickerFromSet
import dev.inmo.tgbotapi.types.files.sticker.Sticker

suspend fun TelegramBot.deleteStickerFromSet(
    sticker: FileId
) = execute(
    DeleteStickerFromSet(
        sticker
    )
)

suspend fun TelegramBot.deleteStickerFromSet(
    sticker: Sticker
) = deleteStickerFromSet(
    sticker.fileId
)
