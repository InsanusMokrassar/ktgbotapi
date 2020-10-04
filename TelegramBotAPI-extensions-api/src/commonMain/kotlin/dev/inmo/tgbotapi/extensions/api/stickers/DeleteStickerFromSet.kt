package dev.inmo.tgbotapi.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.DeleteStickerFromSet
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker

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
