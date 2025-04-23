package dev.inmo.tgbotapi.requests.stickers.abstracts

import dev.inmo.tgbotapi.types.UserId

interface OwnerStickerSetAction : StickerSetAction {
    val userId: UserId
}
