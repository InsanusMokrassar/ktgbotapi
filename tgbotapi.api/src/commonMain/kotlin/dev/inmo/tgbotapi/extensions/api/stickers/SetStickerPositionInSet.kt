package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.SetStickerPositionInSet
import dev.inmo.tgbotapi.types.files.Sticker

public suspend fun TelegramBot.setStickerPositionInSet(
    sticker: FileId,
    position: Int,
): Boolean = execute(
    SetStickerPositionInSet(
        sticker,
        position,
    ),
)

public suspend fun TelegramBot.setStickerPositionInSet(
    sticker: Sticker,
    position: Int,
): Boolean = setStickerPositionInSet(
    sticker.fileId,
    position,
)
