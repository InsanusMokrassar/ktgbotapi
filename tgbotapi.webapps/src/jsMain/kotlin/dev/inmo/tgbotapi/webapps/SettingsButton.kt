package dev.inmo.tgbotapi.webapps

external interface SettingsButton {
    val isVisible: Boolean

    fun onClick(callback: () -> Unit)

    fun offClick(callback: () -> Unit)

    fun show()

    fun hide()
}
