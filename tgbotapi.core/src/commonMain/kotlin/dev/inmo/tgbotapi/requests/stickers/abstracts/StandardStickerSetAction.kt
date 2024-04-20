package dev.inmo.tgbotapi.requests.stickers.abstracts

import dev.inmo.tgbotapi.requests.stickers.InputSticker

interface StandardStickerSetAction : OwnerStickerSetAction {
    val newSticker: InputSticker
}
