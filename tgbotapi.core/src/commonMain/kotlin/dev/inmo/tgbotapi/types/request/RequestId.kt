package dev.inmo.tgbotapi.types.request

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class RequestId(
    val long: Long
)
