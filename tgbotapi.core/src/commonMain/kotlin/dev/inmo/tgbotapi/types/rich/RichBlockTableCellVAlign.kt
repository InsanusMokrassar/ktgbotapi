package dev.inmo.tgbotapi.types.rich

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/** Vertical alignment of a [RichBlockTableCell]. */
@Serializable(RichBlockTableCellVAlign.Serializer::class)
sealed interface RichBlockTableCellVAlign {
    val name: String

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object Top : RichBlockTableCellVAlign {
        override val name: String = "top"
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object Middle : RichBlockTableCellVAlign {
        override val name: String = "middle"
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object Bottom : RichBlockTableCellVAlign {
        override val name: String = "bottom"
    }

    object Serializer : KSerializer<RichBlockTableCellVAlign> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
            "RichBlockTableCellVAlign",
            PrimitiveKind.STRING
        )

        override fun deserialize(decoder: Decoder): RichBlockTableCellVAlign = when (val name = decoder.decodeString()) {
            Top.name -> Top
            Middle.name -> Middle
            Bottom.name -> Bottom
            else -> throw SerializationException("Unknown RichBlockTableCell vertical alignment: $name")
        }

        override fun serialize(encoder: Encoder, value: RichBlockTableCellVAlign) {
            encoder.encodeString(value.name)
        }
    }
}
