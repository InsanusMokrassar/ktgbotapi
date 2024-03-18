package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class InlineQueryId(
    val string: String
) {
    override fun toString(): String {
        return string
    }
}

@Deprecated("Renamed", ReplaceWith("InlineQueryId", "dev.inmo.tgbotapi.types.InlineQueryId"))
typealias InlineQueryIdentifier = InlineQueryId