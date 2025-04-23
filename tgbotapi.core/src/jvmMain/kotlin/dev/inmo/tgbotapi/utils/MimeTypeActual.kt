package dev.inmo.tgbotapi.utils

import kotlinx.serialization.Serializable

@Serializable(MimeTypeSerializer::class)
actual class MimeType(
    actual val raw: String,
) : javax.activation.MimeType(raw)

actual fun createMimeType(raw: String): MimeType = MimeType(raw)
