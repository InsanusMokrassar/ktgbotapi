package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.SetStickerEmojiList
import dev.inmo.tgbotapi.requests.stickers.SetStickerPositionInSet
import dev.inmo.tgbotapi.types.files.Sticker

public suspend fun TelegramBot.setStickerEmojiList(
    sticker: FileId,
    emojis: List<String>
): Unit = execute(
    SetStickerEmojiList(
        sticker,
        emojis
    )
)

public suspend fun TelegramBot.setStickerEmojiList(
    sticker: Sticker,
    vararg emojis: String
): Unit = setStickerEmojiList(
    sticker.fileId,
    emojis.toList()
)
