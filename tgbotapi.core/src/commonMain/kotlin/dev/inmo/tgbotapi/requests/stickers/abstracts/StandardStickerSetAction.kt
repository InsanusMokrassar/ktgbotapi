package dev.inmo.tgbotapi.requests.stickers.abstracts

import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.types.stickers.MaskPosition

interface StandardStickerSetAction : OwnerStickerSetAction {
    val inputSticker: InputSticker
}
