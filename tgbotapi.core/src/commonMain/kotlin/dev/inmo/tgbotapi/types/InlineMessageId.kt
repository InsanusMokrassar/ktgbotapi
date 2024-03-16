package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class InlineMessageId(
    val string: String
)
@Deprecated("Renamed", ReplaceWith("InlineMessageId", "dev.inmo.tgbotapi.types.InlineMessageId"))
typealias InlineMessageIdentifier = InlineMessageId