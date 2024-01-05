package dev.inmo.tgbotapi.types.colors

import dev.inmo.micro_utils.colors.common.HEXAColor
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class ColorId(
    val int: Int
) {
    companion object {
        val defaultAccentColors = mapOf(
            ColorId(0) to HEXAColor(0xff0000ffu),
        )
    }
}