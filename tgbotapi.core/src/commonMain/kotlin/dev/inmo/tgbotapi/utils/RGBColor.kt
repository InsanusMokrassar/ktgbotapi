package dev.inmo.tgbotapi.utils

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class RGBColor(
    val int: Int
)
