package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.SetStickerMaskPosition
import dev.inmo.tgbotapi.types.stickers.MaskPosition

public suspend fun TelegramBot.setStickerMaskPosition(
    sticker: FileId,
    maskPosition: MaskPosition,
): Boolean = execute(
    SetStickerMaskPosition(
        sticker,
        maskPosition,
    ),
)
