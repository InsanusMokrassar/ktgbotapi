package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class StickerSetName(
    val string: String
) {
    override fun toString(): String {
        return string
    }
}
