package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class CallbackQueryId(
    val string: String
)

@Deprecated("Renamed", ReplaceWith("CallbackQueryId", "dev.inmo.tgbotapi.types.CallbackQueryId"))
typealias CallbackQueryIdentifier = CallbackQueryId