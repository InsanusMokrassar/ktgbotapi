package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class RawChatId(
    val long: Long
) {
    companion object {
        val DefaultUserId = RawChatId(136817688L) // I do not know why, it is Telegram crutch
        val FakeUserId = RawChatId(777000L) // Brought with Telegram Bot API 7.9 as backward compatibility value for from field
    }

    override fun toString(): String {
        return long.toString()
    }
}
@Deprecated("Renamed", ReplaceWith("RawChatId", "dev.inmo.tgbotapi.types.RawChatId"))
typealias Identifier = RawChatId