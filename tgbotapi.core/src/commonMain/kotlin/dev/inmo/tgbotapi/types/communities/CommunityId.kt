package dev.inmo.tgbotapi.types.communities

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class CommunityId(
    val long: Long
) {
    override fun toString(): String {
        return long.toString()
    }
}
