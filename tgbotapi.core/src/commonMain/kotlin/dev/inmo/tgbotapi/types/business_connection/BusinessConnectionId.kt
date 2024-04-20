package dev.inmo.tgbotapi.types.business_connection

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class BusinessConnectionId(
    val string: String
) {
    override fun toString(): String {
        return string
    }
}
