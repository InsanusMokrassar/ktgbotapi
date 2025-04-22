package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetStickerMaskPosition(
    @SerialName(stickerField)
    override val sticker: FileId,
    @SerialName(maskPositionField)
    val maskPosition: MaskPosition,
) : StickerAction<Boolean> {
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setStickerMaskPosition"
}
