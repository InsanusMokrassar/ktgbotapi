package dev.inmo.tgbotapi.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(MimeTypeSerializer::class)
expect class MimeType {
    val raw: String
}

private val mimesCache = mutableMapOf<String, MimeType>()

fun String.asMimeType() = buildMimeType(this)

internal expect fun createMimeType(raw: String): MimeType

fun buildMimeType(raw: String): MimeType =
    mimesCache.getOrPut(raw) {
        createMimeType(raw)
    }

@RiskFeature
object MimeTypeSerializer : KSerializer<MimeType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("mimeType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): MimeType {
        val mimeType = decoder.decodeString()
        return mimesCache.getOrPut(mimeType) {
            createMimeType(mimeType)
        }
    }

    override fun serialize(
        encoder: Encoder,
        value: MimeType,
    ) {
        encoder.encodeString(value.raw)
    }
}
