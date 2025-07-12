package dev.inmo.tgbotapi.webapps.haptic

@Suppress("unused", "INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING")
external interface HapticFeedback {
    fun impactOccurred(style: HapticFeedbackStyle)
    fun notificationOccurred(type: HapticFeedbackType)
    fun selectionChanged()
}
