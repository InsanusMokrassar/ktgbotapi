package dev.inmo.tgbotapi.webapps

sealed class EventType(val typeName: String) {
    object ThemeChanged : EventType("themeChanged")
    object ViewportChanged : EventType("viewportChanged")
    object MainButtonClicked : EventType("mainButtonClicked")
}
