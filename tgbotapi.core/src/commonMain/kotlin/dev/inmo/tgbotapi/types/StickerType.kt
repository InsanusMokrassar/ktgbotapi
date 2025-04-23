package dev.inmo.tgbotapi.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(StickerType.Serializer::class)
sealed interface StickerType {
    val type: String

    @Serializable
    object Regular : StickerType {
        override val type: String = "regular"
    }

    @Serializable
    object Mask : StickerType {
        override val type: String = "mask"
    }

    @Serializable
    object CustomEmoji : StickerType {
        override val type: String = "custom_emoji"
    }

    @Serializable
    data class Unknown(override val type: String = "custom_emoji") : StickerType

    object Serializer : KSerializer<StickerType> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor

        override fun deserialize(decoder: Decoder): StickerType {
            return when (val type = decoder.decodeString()) {
                Regular.type -> Regular
                Mask.type -> Mask
                CustomEmoji.type -> CustomEmoji
                else -> Unknown(type)
            }
        }

        override fun serialize(
            encoder: Encoder,
            value: StickerType,
        ) {
            encoder.encodeString(value.type)
        }
    }
}
