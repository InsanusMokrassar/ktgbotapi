package dev.inmo.tgbotapi.requests.stickers.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.UserId
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer

interface StickerSetAction : SimpleRequest<Boolean> {
    val userId: UserId
    val name: String

    override val resultDeserializer: KSerializer<Boolean>
        get() = Boolean.serializer()
}