package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.nameField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeleteStickerSet(
    @SerialName(nameField)
    val name: StickerSetName,
) : SimpleRequest<Boolean> {
    override fun method(): String = "deleteStickerSet"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
