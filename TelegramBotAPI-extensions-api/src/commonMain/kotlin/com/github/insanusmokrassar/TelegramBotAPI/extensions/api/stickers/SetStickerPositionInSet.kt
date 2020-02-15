package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.SetStickerPositionInSet
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker

suspend fun RequestsExecutor.setStickerPositionInSet(
    sticker: FileId,
    position: Int
) = execute(
    SetStickerPositionInSet(
        sticker,
        position
    )
)

suspend fun RequestsExecutor.setStickerPositionInSet(
    sticker: Sticker,
    position: Int
) = setStickerPositionInSet(
    sticker.fileId,
    position
)
