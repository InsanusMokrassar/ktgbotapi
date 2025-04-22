package dev.inmo.tgbotapi.webapps.biometric

import kotlinx.serialization.Serializable

@Serializable
value class BiometricType(
    val title: String,
) {
    val isFinger: Boolean
        get() = title == FingerTypeTitle
    val isFace: Boolean
        get() = title == FaceTypeTitle
    val isUnknown: Boolean
        get() = title == UnknownTypeTitle

    companion object {
        const val FingerTypeTitle = "finger"
        const val FaceTypeTitle = "face"
        const val UnknownTypeTitle = "unknown"
    }
}
