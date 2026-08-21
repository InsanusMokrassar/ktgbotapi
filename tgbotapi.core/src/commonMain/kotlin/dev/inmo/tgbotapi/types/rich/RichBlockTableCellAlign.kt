package dev.inmo.tgbotapi.types.rich

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/** Horizontal alignment of a [RichBlockTableCell]. */
@Serializable(RichBlockTableCellAlign.Serializer::class)
sealed interface RichBlockTableCellAlign {
    val name: String

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object Left : RichBlockTableCellAlign {
        override val name: String = "left"
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object Center : RichBlockTableCellAlign {
        override val name: String = "center"
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data object Right : RichBlockTableCellAlign {
        override val name: String = "right"
    }

    object Serializer : KSerializer<RichBlockTableCellAlign> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
            "RichBlockTableCellAlign",
            PrimitiveKind.STRING
        )

        override fun deserialize(decoder: Decoder): RichBlockTableCellAlign = when (val name = decoder.decodeString()) {
            Left.name -> Left
            Center.name -> Center
            Right.name -> Right
            else -> throw SerializationException("Unknown RichBlockTableCell alignment: $name")
        }

        override fun serialize(encoder: Encoder, value: RichBlockTableCellAlign) {
            encoder.encodeString(value.name)
        }
    }
}
