package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.SetStickerPositionInSet
import dev.inmo.tgbotapi.types.files.Sticker

public suspend fun TelegramBot.setStickerPositionInSet(
    sticker: FileId,
    position: Int
): Unit = execute(
    SetStickerPositionInSet(
        sticker,
        position
    )
)

public suspend fun TelegramBot.setStickerPositionInSet(
    sticker: Sticker,
    position: Int
): Unit = setStickerPositionInSet(
    sticker.fileId,
    position
)
