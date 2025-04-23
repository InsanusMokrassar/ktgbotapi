package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.DeleteStickerFromSet
import dev.inmo.tgbotapi.types.files.Sticker

public suspend fun TelegramBot.deleteStickerFromSet(sticker: FileId): Boolean = execute(
    DeleteStickerFromSet(
        sticker,
    ),
)

public suspend fun TelegramBot.deleteStickerFromSet(sticker: Sticker): Boolean = deleteStickerFromSet(
    sticker.fileId,
)
