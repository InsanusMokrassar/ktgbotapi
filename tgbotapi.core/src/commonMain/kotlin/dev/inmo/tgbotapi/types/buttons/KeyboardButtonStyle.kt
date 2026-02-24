package dev.inmo.tgbotapi.types.buttons

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(KeyboardButtonStyle.Serializer::class)
sealed interface KeyboardButtonStyle {
    val name: String

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(KeyboardButtonStyle.Serializer::class)
    data object Danger : KeyboardButtonStyle { override val name: String = "danger" }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(KeyboardButtonStyle.Serializer::class)
    data object Success : KeyboardButtonStyle { override val name: String = "success" }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(KeyboardButtonStyle.Serializer::class)
    data object Primary : KeyboardButtonStyle { override val name: String = "primary" }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(KeyboardButtonStyle.Serializer::class)
    data class Custom(override val name: String = "primary") : KeyboardButtonStyle

    object Serializer : KSerializer<KeyboardButtonStyle> {
        override val descriptor: SerialDescriptor
            get() = String.serializer().descriptor

        override fun serialize(
            encoder: Encoder,
            value: KeyboardButtonStyle
        ) {
            encoder.encodeString(value.name)
        }

        override fun deserialize(decoder: Decoder): KeyboardButtonStyle {
            return when (val rawValue = decoder.decodeString()) {
                Danger.name -> Danger
                Success.name -> Success
                Primary.name -> Primary
                else -> Custom(rawValue)
            }
        }

    }
}