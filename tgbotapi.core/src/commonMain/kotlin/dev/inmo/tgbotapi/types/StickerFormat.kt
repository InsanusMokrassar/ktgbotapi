package dev.inmo.tgbotapi.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(StickerFormat.Serializer::class)
sealed interface StickerFormat {
    val type: String

    @Serializable
    object Static : StickerFormat { override val type: String = "static" }
    @Serializable
    object Animated : StickerFormat { override val type: String = "animated" }
    @Serializable
    object Video : StickerFormat { override val type: String = "video" }
    @Serializable
    data class Unknown(override val type: String = "custom_emoji") : StickerFormat

    object Serializer : KSerializer<StickerFormat> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor

        override fun deserialize(decoder: Decoder): StickerFormat {
            return when (val type = decoder.decodeString()) {
                Static.type -> Static
                Animated.type -> Animated
                Video.type -> Video
                else -> Unknown(type)
            }
        }

        override fun serialize(encoder: Encoder, value: StickerFormat) {
            encoder.encodeString(value.type)
        }

    }
}