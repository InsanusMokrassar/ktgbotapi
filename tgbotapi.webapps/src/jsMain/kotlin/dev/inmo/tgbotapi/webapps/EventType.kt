package dev.inmo.tgbotapi.webapps

sealed class EventType(val typeName: String) {
    data object ThemeChanged : EventType("themeChanged")
    data object ViewportChanged : EventType("viewportChanged")
    data object MainButtonClicked : EventType("mainButtonClicked")
    data object SecondaryButtonClicked : EventType("secondaryButtonClicked")
    data object BackButtonClicked : EventType("backButtonClicked")
    data object SettingsButtonClicked : EventType("settingsButtonClicked")
    data object InvoiceClosed : EventType("invoiceClosed")
    data object PopupClosed : EventType("popupClosed")
    data object QRTextReceived : EventType("qrTextReceived")
    data object ClipboardTextReceived : EventType("clipboardTextReceived")
    data object WriteAccessRequested : EventType("writeAccessRequested")
    data object ContactRequested : EventType("contactRequested")
    data object ScanQRPopupClosed : EventType("scanQrPopupClosed")
}
