package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.stickerSetNameField
import dev.inmo.tgbotapi.types.stickers.StickerSet
import kotlinx.serialization.*

@Serializable
data class GetStickerSet(
    @SerialName(stickerSetNameField)
    val name: String
): SimpleRequest<StickerSet> {
    override fun method(): String = "getStickerSet"
    override val resultDeserializer: DeserializationStrategy<StickerSet>
        get() = StickerSet.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
