package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.CustomEmojiSticker
import dev.inmo.tgbotapi.types.files.StickerSerializer
import dev.inmo.tgbotapi.types.stickers.StickerSet
import kotlinx.serialization.*

@Serializable
data class GetCustomEmojiStickers(
    @SerialName(customEmojiIdsField)
    val customEmojiIds: List<CustomEmojiId>
): SimpleRequest<CustomEmojiSticker> {
    override fun method(): String = "getCustomEmojiStickers"
    override val resultDeserializer: DeserializationStrategy<CustomEmojiSticker>
        get() = StickerSerializer as DeserializationStrategy<CustomEmojiSticker>
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
