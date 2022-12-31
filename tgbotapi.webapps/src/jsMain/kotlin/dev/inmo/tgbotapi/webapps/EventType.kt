package dev.inmo.tgbotapi.webapps

sealed class EventType(val typeName: String) {
    object ThemeChanged : EventType("themeChanged")
    object ViewportChanged : EventType("viewportChanged")
    object MainButtonClicked : EventType("mainButtonClicked")
    object BackButtonClicked : EventType("backButtonClicked")
    object SettingsButtonClicked : EventType("settingsButtonClicked")
    object InvoiceClosed : EventType("invoiceClosed")
    object PopupClosed : EventType("popupClosed")
    object QRTextReceived : EventType("qrTextReceived")
    object ClipboardTextReceived : EventType("clipboardTextReceived")
}
