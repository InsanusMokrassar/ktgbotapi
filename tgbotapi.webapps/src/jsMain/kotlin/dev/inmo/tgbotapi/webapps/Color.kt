package dev.inmo.tgbotapi.webapps

import dev.inmo.micro_utils.common.Warning
import kotlinx.serialization.Serializable

sealed interface Color {
    val value: String

    @Serializable
    value class BackgroundColor
    @Warning("This constructor is not supposed to be called ouside of ktgbotapi library")
    constructor(
        override val value: String,
    ) : Color

    @Serializable
    value class Hex(override val value: String) : Color {
        constructor(
            r: UByte,
            g: UByte,
            b: UByte,
        ) : this("#${r.toString(16).padStart(2, '0')}${g.toString(16).padStart(2, '0')}${b.toString(16).padStart(2, '0')}")
    }

    companion object {
        val BackgroundColor = BackgroundColor("bg_color")
        val SecondaryBackgroundColor = BackgroundColor("secondary_bg_color")
        val BottomBarBackgroundColor = BackgroundColor("bottom_bar_bg_color")

        @Suppress("NOTHING_TO_INLINE")
        inline operator fun invoke(value: String) = Hex(value)
    }
}
