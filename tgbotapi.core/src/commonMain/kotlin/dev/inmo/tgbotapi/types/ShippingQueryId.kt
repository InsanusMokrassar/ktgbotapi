package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class ShippingQueryId(
    val string: String
) {
    override fun toString(): String {
        return string
    }
}

@Deprecated("Renamed", ReplaceWith("ShippingQueryId", "dev.inmo.tgbotapi.types.ShippingQueryId"))
typealias ShippingQueryIdentifier = ShippingQueryId