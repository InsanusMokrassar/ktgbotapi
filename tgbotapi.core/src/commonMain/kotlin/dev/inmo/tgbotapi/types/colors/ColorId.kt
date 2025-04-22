package dev.inmo.tgbotapi.types.colors

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class ColorId(
    val int: Int,
)
