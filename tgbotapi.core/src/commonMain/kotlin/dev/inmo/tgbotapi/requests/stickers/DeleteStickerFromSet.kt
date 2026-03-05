package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerAction
import dev.inmo.tgbotapi.types.stickerField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeleteStickerFromSet(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(stickerField)
    override val sticker: FileId
) : StickerAction<Unit> {
    override fun method(): String = "deleteStickerFromSet"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
