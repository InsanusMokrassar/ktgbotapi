package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.SetStickerKeywords
import dev.inmo.tgbotapi.types.files.Sticker

public suspend fun TelegramBot.setStickerKeywords(
    sticker: FileId,
    keywords: List<String>,
): Boolean = execute(
    SetStickerKeywords(
        sticker,
        keywords,
    ),
)

public suspend fun TelegramBot.setStickerKeywords(
    sticker: Sticker,
    vararg keywords: String,
): Boolean = setStickerKeywords(
    sticker.fileId,
    keywords.toList(),
)
