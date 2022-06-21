package dev.inmo.tgbotapi.webapps.haptic

import dev.inmo.micro_utils.common.Warning
import kotlinx.serialization.Serializable

@Serializable
value class HapticFeedbackType @Warning("Do not use this constructor if available objects from companion cover your needs") constructor(
    val name: String
) {
    companion object {
        val Error = HapticFeedbackType("error")
        val Success = HapticFeedbackType("success")
        val Warning = HapticFeedbackType("warning")
    }
}
