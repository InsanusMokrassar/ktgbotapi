package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class ShippingOptionId(
    val string: String
) {
    override fun toString(): String {
        return string
    }
}
@Deprecated("ShippingOptionId", ReplaceWith("ShippingOptionId", "dev.inmo.tgbotapi.types.ShippingOptionId"))
typealias ShippingOptionIdentifier = ShippingOptionId