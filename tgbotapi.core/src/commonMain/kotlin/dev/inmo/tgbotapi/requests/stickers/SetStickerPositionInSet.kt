package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerAction
import dev.inmo.tgbotapi.types.positionField
import dev.inmo.tgbotapi.types.stickerField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetStickerPositionInSet(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(stickerField)
    override val sticker: FileId,
    @SerialName(positionField)
    val position: Int
) : StickerAction<Unit> {
    init {
        if (position < 0) {
            throw IllegalArgumentException("Position must be positive or 0")
        }
    }

    override fun method(): String = "setStickerPositionInSet"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
