package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.DeleteStickerFromSet
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker

suspend fun RequestsExecutor.deleteStickerFromSet(
    sticker: FileId
) = execute(
    DeleteStickerFromSet(
        sticker
    )
)

suspend fun RequestsExecutor.deleteStickerFromSet(
    sticker: Sticker
) = deleteStickerFromSet(
    sticker.fileId
)
