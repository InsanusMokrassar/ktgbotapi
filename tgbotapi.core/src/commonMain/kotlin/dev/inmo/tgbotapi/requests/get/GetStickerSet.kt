package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.nameField
import dev.inmo.tgbotapi.types.stickers.StickerSet
import kotlinx.serialization.*

@Serializable
data class GetStickerSet(
    @SerialName(nameField)
    val name: StickerSetName,
) : SimpleRequest<StickerSet> {
    override fun method(): String = "getStickerSet"

    override val resultDeserializer: DeserializationStrategy<StickerSet>
        get() = StickerSet.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
