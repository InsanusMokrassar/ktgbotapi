package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class InlineQueryId(
    val string: String
)

@Deprecated("Renamed", ReplaceWith("InlineQueryId", "dev.inmo.tgbotapi.types.InlineQueryId"))
typealias InlineQueryIdentifier = InlineQueryId