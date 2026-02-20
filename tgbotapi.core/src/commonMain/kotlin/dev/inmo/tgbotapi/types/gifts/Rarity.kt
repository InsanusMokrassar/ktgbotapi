package dev.inmo.tgbotapi.types.gifts

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(Rarity.Companion::class)
sealed interface Rarity {
    val name: String

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Rarity.Companion::class)
    data object Uncommon : Rarity { override val name: String = "uncommon" }
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Rarity.Companion::class)
    data object Rare : Rarity { override val name: String = "rare" }
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Rarity.Companion::class)
    data object Epic : Rarity { override val name: String = "epic" }
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Rarity.Companion::class)
    data object Legendary : Rarity { override val name: String = "legendary" }
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Rarity.Companion::class)
    data class Custom(override val name: String) : Rarity

    companion object : KSerializer<Rarity> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor

        fun fromString(value: String): Rarity = when (value) {
            Uncommon.name -> Uncommon
            Rare.name -> Rare
            Epic.name -> Epic
            Legendary.name -> Legendary
            else -> Custom(value)
        }

        override fun deserialize(decoder: Decoder): Rarity {
            val value = decoder.decodeString()
            return fromString(value)
        }

        override fun serialize(
            encoder: Encoder,
            value: Rarity
        ) {
            encoder.encodeString(value.name)
        }
    }
}
