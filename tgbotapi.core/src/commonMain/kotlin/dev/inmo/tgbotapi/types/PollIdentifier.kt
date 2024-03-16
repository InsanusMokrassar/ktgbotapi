package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class PollId(
    val string: String
) {
    override fun toString(): String {
        return string
    }
}
@Deprecated("Renamed", ReplaceWith("PollId", "dev.inmo.tgbotapi.types.PollId"))
typealias PollIdentifier = String
