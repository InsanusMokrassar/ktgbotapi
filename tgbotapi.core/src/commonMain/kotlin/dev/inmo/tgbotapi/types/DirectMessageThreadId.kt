package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class DirectMessageThreadId(
    val long: Long
) {
    override fun toString(): String {
        return long.toString()
    }
}
