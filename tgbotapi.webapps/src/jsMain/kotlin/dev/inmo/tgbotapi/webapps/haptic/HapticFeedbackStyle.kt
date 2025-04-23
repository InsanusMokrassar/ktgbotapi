package dev.inmo.tgbotapi.webapps.haptic

import dev.inmo.micro_utils.common.Warning
import kotlinx.serialization.Serializable

@Serializable
value class HapticFeedbackStyle
@Warning("Do not use this constructor if available objects from companion cover your needs")
constructor(
    val name: String,
) {
    companion object {
        val Light = HapticFeedbackStyle("light")
        val Medium = HapticFeedbackStyle("medium")
        val Heavy = HapticFeedbackStyle("heavy")
        val Rigid = HapticFeedbackStyle("rigid")
        val Soft = HapticFeedbackStyle("soft")
    }
}
