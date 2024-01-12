package dev.inmo.tgbotapi.types.boosts

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class BoostId(
    val string: String
)
