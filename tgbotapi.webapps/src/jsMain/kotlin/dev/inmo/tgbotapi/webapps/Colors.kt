package dev.inmo.tgbotapi.webapps

import kotlinx.serialization.Serializable

sealed interface Color {
    val value: String
    @Serializable
    value class BackgroundColor(override val value: String) : Color

    @Serializable
    value class Hex(override val value: String) : Color

    companion object {
        val BackgroundColor = BackgroundColor("bg_color")
        val SecondaryBackgroundColor = BackgroundColor("secondary_bg_color")

        @Suppress("NOTHING_TO_INLINE")
        inline operator fun invoke(value: String) = Hex(value)
    }
}
