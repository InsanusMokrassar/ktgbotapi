package dev.inmo.tgbotapi.utils

import kotlinx.serialization.Serializable

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@Serializable(MimeTypeSerializer::class)
actual class MimeType(
    actual val raw: String
) : javax.activation.MimeType(raw)

actual fun createMimeType(raw: String): MimeType = MimeType(raw)
