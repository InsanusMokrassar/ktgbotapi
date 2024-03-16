package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class UpdateId(
    val long: Long
) : Comparable<UpdateId> {
    operator fun plus(long: Long) = UpdateId(this.long + long)
    operator fun minus(long: Long) = UpdateId(this.long - long)

    override fun compareTo(other: UpdateId): Int {
        return long.compareTo(other.long)
    }
}

@Deprecated("Renamed", ReplaceWith("UpdateId", "dev.inmo.tgbotapi.types.UpdateId"))
typealias UpdateIdentifier = UpdateId
