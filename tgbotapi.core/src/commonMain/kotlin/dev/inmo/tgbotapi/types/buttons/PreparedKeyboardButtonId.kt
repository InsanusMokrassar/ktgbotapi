package dev.inmo.tgbotapi.types.buttons

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class PreparedKeyboardButtonId(
    val string: String
)