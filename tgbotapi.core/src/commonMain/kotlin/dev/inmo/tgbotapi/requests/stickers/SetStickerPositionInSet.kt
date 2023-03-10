package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerAction
import dev.inmo.tgbotapi.types.positionField
import dev.inmo.tgbotapi.types.stickerField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetStickerPositionInSet(
    @SerialName(stickerField)
    override val sticker: FileId,
    @SerialName(positionField)
    val position: Int
) : StickerAction<Boolean> {
    init {
        if (position < 0) {
            throw IllegalArgumentException("Position must be positive or 0")
        }
    }

    override fun method(): String = "setStickerPositionInSet"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
