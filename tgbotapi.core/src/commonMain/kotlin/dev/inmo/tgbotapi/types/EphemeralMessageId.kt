package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class EphemeralMessageId(
    val long: Long
) {
    override fun toString(): String {
        return long.toString()
    }
}

fun Long.asEphemeralMessageId() = EphemeralMessageId(this)
