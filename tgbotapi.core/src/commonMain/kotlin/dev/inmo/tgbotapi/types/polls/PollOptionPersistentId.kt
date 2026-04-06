package dev.inmo.tgbotapi.types.polls

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class PollOptionPersistentId(
    val string: String
)
