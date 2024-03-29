package dev.inmo.tgbotapi.utils

import kotlinx.serialization.Serializable

//actual typealias MimeType = MimeType

@OptIn(RiskFeature::class)
@Serializable(MimeTypeSerializer::class)
actual data class MimeType(
    actual val raw: String
)
internal actual fun createMimeType(raw: String): MimeType = MimeType(raw)
