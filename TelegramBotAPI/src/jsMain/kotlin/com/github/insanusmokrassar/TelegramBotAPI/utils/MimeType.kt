package com.github.insanusmokrassar.TelegramBotAPI.utils

import kotlinx.serialization.*
import org.w3c.dom.get
import kotlin.browser.window

private val mimesCache = mutableMapOf<String, MimeType>()

@Serializable(MimeTypeSerializer::class)
actual class MimeType(
    actual val raw: String
) {
    @Transient
    val jsMimeType = window.navigator.mimeTypes[raw]

    override fun toString(): String = raw
}

@Serializer(MimeType::class)
internal actual object MimeTypeSerializer : KSerializer<MimeType> {
    override val descriptor: SerialDescriptor = PrimitiveDescriptor("mimeType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): MimeType {
        val mimeType = decoder.decodeString()
        return mimesCache.getOrPut(mimeType) {
            buildMimeType(mimeType)
        }
    }

    override fun serialize(encoder: Encoder, value: MimeType) {
        encoder.encodeString(value.raw)
    }
}

actual fun buildMimeType(raw: String) = MimeType(raw)
