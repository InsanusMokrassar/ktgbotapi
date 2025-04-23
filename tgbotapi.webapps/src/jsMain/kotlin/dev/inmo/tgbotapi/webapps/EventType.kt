package dev.inmo.tgbotapi.webapps

import dev.inmo.micro_utils.ksp.sealed.GenerateSealedWorkaround

@GenerateSealedWorkaround
sealed class EventType(val typeName: String) {
    data object EmojiStatusAccessRequested : EventType("emojiStatusAccessRequested")

    data object AccelerometerChanged : EventType("accelerometerChanged")

    data object DeviceOrientationStarted : EventType("deviceOrientationStarted")

    data object EmojiStatusFailed : EventType("emojiStatusFailed")

    data object Activated : EventType("activated")

    data object ShareMessageSent : EventType("shareMessageSent")

    data object LocationManagerUpdated : EventType("locationManagerUpdated")

    data object BiometricTokenUpdated : EventType("biometricTokenUpdated")

    data object DeviceOrientationFailed : EventType("deviceOrientationFailed")

    data object SafeAreaChanged : EventType("safeAreaChanged")

    data object WriteAccessRequested : EventType("writeAccessRequested")

    data object ContentSafeAreaChanged : EventType("contentSafeAreaChanged")

    data object AccelerometerStarted : EventType("accelerometerStarted")

    data object AccelerometerStopped : EventType("accelerometerStopped")

    data object PopupClosed : EventType("popupClosed")

    data object GyroscopeStopped : EventType("gyroscopeStopped")

    data object BackButtonClicked : EventType("backButtonClicked")

    data object HomeScreenAdded : EventType("homeScreenAdded")

    data object ShareMessageFailed : EventType("shareMessageFailed")

    data object ThemeChanged : EventType("themeChanged")

    data object BiometricManagerUpdated : EventType("biometricManagerUpdated")

    data object ScanQrPopupClosed : EventType("scanQrPopupClosed")

    data object BiometricAuthRequested : EventType("biometricAuthRequested")

    data object HomeScreenChecked : EventType("homeScreenChecked")

    data object LocationRequested : EventType("locationRequested")

    data object SecondaryButtonClicked : EventType("secondaryButtonClicked")

    data object QrTextReceived : EventType("qrTextReceived")

    data object EmojiStatusSet : EventType("emojiStatusSet")

    data object FullscreenFailed : EventType("fullscreenFailed")

    data object GyroscopeFailed : EventType("gyroscopeFailed")

    data object SettingsButtonClicked : EventType("settingsButtonClicked")

    data object Deactivated : EventType("deactivated")

    data object DeviceOrientationStopped : EventType("deviceOrientationStopped")

    data object FullscreenChanged : EventType("fullscreenChanged")

    data object ViewportChanged : EventType("viewportChanged")

    data object MainButtonClicked : EventType("mainButtonClicked")

    data object AccelerometerFailed : EventType("accelerometerFailed")

    data object ClipboardTextReceived : EventType("clipboardTextReceived")

    data object FileDownloadRequested : EventType("fileDownloadRequested")

    data object ContactRequested : EventType("contactRequested")

    data object InvoiceClosed : EventType("invoiceClosed")

    data object GyroscopeStarted : EventType("gyroscopeStarted")

    data object GyroscopeChanged : EventType("gyroscopeChanged")

    data object DeviceOrientationChanged : EventType("deviceOrientationChanged")

    companion object
}
