package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.SetStickerPositionInSet
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker

suspend fun TelegramBot.setStickerPositionInSet(
    sticker: FileId,
    position: Int
) = execute(
    SetStickerPositionInSet(
        sticker,
        position
    )
)

suspend fun TelegramBot.setStickerPositionInSet(
    sticker: Sticker,
    position: Int
) = setStickerPositionInSet(
    sticker.fileId,
    position
)
