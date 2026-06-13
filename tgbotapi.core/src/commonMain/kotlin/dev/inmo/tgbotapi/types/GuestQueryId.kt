package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class GuestQueryId(
    val string: String
) {
    override fun toString(): String {
        return string
    }
}
