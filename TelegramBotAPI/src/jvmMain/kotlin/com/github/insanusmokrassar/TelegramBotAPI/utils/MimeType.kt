package com.github.insanusmokrassar.TelegramBotAPI.utils

import kotlinx.serialization.*

private val mimesCache = mutableMapOf<String, MimeType>()

@Serializable(MimeTypeSerializer::class)
actual class MimeType(
    actual val raw: String
) : javax.activation.MimeType(raw)

@Serializer(MimeType::class)
internal actual object MimeTypeSerializer : KSerializer<MimeType> {
    override val descriptor: SerialDescriptor = PrimitiveDescriptor("mimeType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): MimeType {
        val mimeType = decoder.decodeString()
        return mimesCache.getOrPut(mimeType) {
            MimeType(mimeType)
        }
    }

    override fun serialize(encoder: Encoder, value: MimeType) {
        encoder.encodeString(value.raw)
    }
}

actual fun buildMimeType(raw: String): MimeType = MimeType(raw)
