package dev.inmo.tgbotapi.requests.stickers.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.UserId
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer

interface OwnerStickerSetAction : StickerSetAction {
    val userId: UserId
}
