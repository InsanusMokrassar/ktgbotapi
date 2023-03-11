package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.SetStickerEmojiList
import dev.inmo.tgbotapi.requests.stickers.SetStickerKeywords
import dev.inmo.tgbotapi.requests.stickers.SetStickerPositionInSet
import dev.inmo.tgbotapi.types.files.Sticker

suspend fun TelegramBot.setStickerKeywords(
    sticker: FileId,
    keywords: List<String>
) = execute(
    SetStickerKeywords(
        sticker,
        keywords
    )
)

suspend fun TelegramBot.setStickerKeywords(
    sticker: Sticker,
    vararg keywords: String
) = setStickerKeywords(
    sticker.fileId,
    keywords.toList()
)
