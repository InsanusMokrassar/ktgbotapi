package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.CustomEmojiSticker
import dev.inmo.tgbotapi.types.files.StickerSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

internal val getCustomEmojiStickersResultSerializer = ListSerializer(StickerSerializer) as DeserializationStrategy<List<CustomEmojiSticker>>

@Serializable
data class GetCustomEmojiStickers(
    @SerialName(customEmojiIdsField)
    val customEmojiIds: List<CustomEmojiId>
): SimpleRequest<List<CustomEmojiSticker>> {
    override fun method(): String = "getCustomEmojiStickers"
    override val resultDeserializer: DeserializationStrategy<List<CustomEmojiSticker>>
        get() = getCustomEmojiStickersResultSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
