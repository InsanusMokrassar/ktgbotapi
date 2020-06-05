package com.github.insanusmokrassar.TelegramBotAPI.utils

import kotlinx.serialization.*

@Serializable(MimeTypeSerializer::class)
expect class MimeType {
    val raw: String
}

expect fun buildMimeType(raw: String): MimeType

@Serializer(MimeType::class)
internal expect object MimeTypeSerializer : KSerializer<MimeType>
