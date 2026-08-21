package dev.inmo.tgbotapi.types.rich

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Type of an ordered [InputRichBlockListItem] label.
 */
@Serializable(LabelType.Serializer::class)
sealed interface LabelType {
    val typeSymbol: String

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object LettersUppercase : LabelType {
        override val typeSymbol: String = "A"
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object LettersLowercase : LabelType {
        override val typeSymbol: String = "a"
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object RomanUppercase : LabelType {
        override val typeSymbol: String = "I"
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object RomanLowercase : LabelType {
        override val typeSymbol: String = "i"
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object Decimals : LabelType {
        override val typeSymbol: String = "1"
    }

    object Serializer : KSerializer<LabelType> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LabelType", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): LabelType = when (val typeSymbol = decoder.decodeString()) {
            LettersUppercase.typeSymbol -> LettersUppercase
            LettersLowercase.typeSymbol -> LettersLowercase
            RomanUppercase.typeSymbol -> RomanUppercase
            RomanLowercase.typeSymbol -> RomanLowercase
            Decimals.typeSymbol -> Decimals
            else -> throw SerializationException("Unknown InputRichBlockListItem label type: $typeSymbol")
        }

        override fun serialize(encoder: Encoder, value: LabelType) {
            encoder.encodeString(value.typeSymbol)
        }
    }
}