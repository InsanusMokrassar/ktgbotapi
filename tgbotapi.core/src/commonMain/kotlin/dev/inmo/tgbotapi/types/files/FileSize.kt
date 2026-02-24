package dev.inmo.tgbotapi.types.files

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class FileSize(
    val bytes: ULong
) : Comparable<FileSize> {
    override fun compareTo(other: FileSize): Int = bytes.compareTo(other.bytes)
}
