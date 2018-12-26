package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent

data class StickerContent(
    override val media: Sticker
) : MediaContent<Sticker>
