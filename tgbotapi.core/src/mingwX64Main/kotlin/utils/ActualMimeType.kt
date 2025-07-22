package dev.inmo.tgbotapi.utils

import kotlinx.serialization.Serializable

//actual typealias MimeType = MimeType

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@Serializable(MimeTypeSerializer::class)
actual data class MimeType(
    actual val raw: String
)
internal actual fun createMimeType(raw: String): MimeType = MimeType(raw)
