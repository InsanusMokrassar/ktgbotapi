package dev.inmo.tgbotapi.webapps.haptic

external interface HapticFeedback {
    fun impactOccurred(style: HapticFeedbackStyle)

    fun notificationOccurred(type: HapticFeedbackType)

    fun selectionChanged()
}
