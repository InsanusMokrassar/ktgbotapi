package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition

interface StandardStickerSetAction : StickerSetAction {
    val emojis: String // must be more than one
    val maskPosition: MaskPosition?
}