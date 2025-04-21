package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.PreparedMessageId
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.webapps.accelerometer.Accelerometer
import dev.inmo.tgbotapi.webapps.biometric.BiometricManager
import dev.inmo.tgbotapi.webapps.cloud.CloudStorage
import dev.inmo.tgbotapi.webapps.gyroscope.Gyroscope
import dev.inmo.tgbotapi.webapps.haptic.HapticFeedback
import dev.inmo.tgbotapi.webapps.invoice.InvoiceClosedInfo
import dev.inmo.tgbotapi.webapps.location.LocationManager
import dev.inmo.tgbotapi.webapps.orientation.DeviceOrientation
import dev.inmo.tgbotapi.webapps.popup.*
import dev.inmo.tgbotapi.webapps.storage.DeviceStorage
import dev.inmo.tgbotapi.webapps.storage.SecureStorage
import dev.inmo.tgbotapi.webapps.stories.StoryShareParams

external class WebApp {
    val version: String

    val platform: String

    val initData: String
    val initDataUnsafe: WebAppInitData

    val headerColor: HEXColor?
    fun setHeaderColor(color: Color.BackgroundColor)
    fun setHeaderColor(color: Color.Hex)

    val backgroundColor: HEXColor?
    fun setBackgroundColor(color: Color.Hex)
    fun setBackgroundColor(color: Color.BackgroundColor)

    val bottomBarColor: HEXColor?
    fun setBottomBarColor(color: Color.Hex)
    fun setBottomBarColor(color: Color.BackgroundColor)

    @JsName("colorScheme")
    val colorSchemeRaw: String
    val themeParams: ThemeParams

    val isExpanded: Boolean
    val viewportHeight: Float
    val viewportStableHeight: Float

    val isVerticalSwipesEnabled: Boolean
    fun enableVerticalSwipes()
    fun disableVerticalSwipes()


    val isClosingConfirmationEnabled: Boolean
    fun enableClosingConfirmation()
    fun disableClosingConfirmation()

    fun showPopup(params: PopupParams, callback: ClosePopupCallback? = definedExternally)
    fun showAlert(message: String, callback: AlertCallback? = definedExternally)
    fun showConfirm(message: String, callback: ConfirmCallback? = definedExternally)
    fun showScanQrPopup(params: ScanQrPopupParams, callback: QRTextReceivedCallback? = definedExternally)
    fun closeScanQrPopup()
    fun readTextFromClipboard(callback: TextReceivedCallback? = definedExternally)

    val isActive: Boolean
    val isFullscreen: Boolean
    val safeAreaInset: SafeAreaInset
    val contentSafeAreaInset: ContentSafeAreaInset
    fun requestFullscreen()
    fun exitFullscreen()

    val isOrientationLocked: Boolean
    fun lockOrientation()
    fun unlockOrientation()

    fun addToHomeScreen()
    fun checkHomeScreenStatus(callback: (HomeScreenStatus) -> Unit = definedExternally)

    @JsName("MainButton")
    val mainButton: BottomButton
    @JsName("SecondaryButton")
    val secondaryButton: BottomButton

    @JsName("BackButton")
    val backButton: BackButton

    @JsName("HapticFeedback")
    val hapticFeedback: HapticFeedback

    @JsName("CloudStorage")
    val cloudStorage: CloudStorage

    @JsName("BiometricManager")
    val biometricManager: BiometricManager

    @JsName("Accelerometer")
    val accelerometer: Accelerometer

    @JsName("DeviceOrientation")
    val deviceOrientation: DeviceOrientation

    @JsName("Gyroscope")
    val gyroscope: Gyroscope

    @JsName("LocationManager")
    val locationManager: LocationManager

    @JsName("SettingsButton")
    val settingsButton: SettingsButton

    @JsName("DeviceStorage")
    val deviceStorage: DeviceStorage

    @JsName("SecureStorage")
    val secureStorage: SecureStorage


    internal fun onEvent(type: String, callback: () -> Unit)

    fun offEvent(type: String, callback: () -> Unit)
    @JsName("offEvent")
    fun offEventWithViewportChangedData(type: String, callback: (ViewportChangedData) -> Unit)
    @JsName("offEvent")
    fun offEventWithInvoiceClosedInfo(type: String, callback: (InvoiceClosedInfo) -> Unit)

    fun sendData(data: String)

    fun ready()
    fun expand()
    fun close()

    fun isVersionAtLeast(version: String): Boolean
    fun openLink(url: String)
    fun openTelegramLink(url: String)
    fun openInvoice(url: String, callback: (InvoiceClosedInfo) -> Unit = definedExternally)

    fun shareToStory(mediaUrl: String, params: StoryShareParams? = definedExternally)

    fun shareMessage(messageId: PreparedMessageId, callback: (Boolean) -> Unit = definedExternally)

    fun setEmojiStatus(customEmojiId: CustomEmojiId, params: EmojiStatusParams = definedExternally, callback: (Boolean) -> Unit = definedExternally)
    fun requestEmojiStatusAccess(callback: (Boolean) -> Unit = definedExternally)

    fun downloadFile(params: DownloadFileParams, callback: (Boolean) -> Unit = definedExternally)

    fun requestWriteAccess(callback: ((Boolean) -> Unit)? = definedExternally)
    fun requestContact(callback: ((Boolean) -> Unit)? = definedExternally)

    // Start of generated part

    @JsName("onEvent")
    internal fun onEmojiStatusAccessRequested(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit)
    @JsName("onEvent")
    internal fun onAccelerometerChanged(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onDeviceOrientationStarted(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onEmojiStatusFailed(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit)
    @JsName("onEvent")
    internal fun onActivated(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onShareMessageSent(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onLocationManagerUpdated(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onBiometricTokenUpdated(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgIsUpdatedObject) -> Unit)
    @JsName("onEvent")
    internal fun onDeviceOrientationFailed(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit)
    @JsName("onEvent")
    internal fun onSafeAreaChanged(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onWriteAccessRequested(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit)
    @JsName("onEvent")
    internal fun onContentSafeAreaChanged(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onAccelerometerStarted(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onAccelerometerStopped(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onPopupClosed(type: String, callback: (dev.inmo.tgbotapi.webapps.popup.PopupClosedEventArg) -> Unit)
    @JsName("onEvent")
    internal fun onGyroscopeStopped(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onBackButtonClicked(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onHomeScreenAdded(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onShareMessageFailed(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit)
    @JsName("onEvent")
    internal fun onThemeChanged(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onBiometricManagerUpdated(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onScanQrPopupClosed(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onBiometricAuthRequested(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgBiometricAuthRequested) -> Unit)
    @JsName("onEvent")
    internal fun onHomeScreenChecked(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit)
    @JsName("onEvent")
    internal fun onLocationRequested(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgLocationDataObject) -> Unit)
    @JsName("onEvent")
    internal fun onSecondaryButtonClicked(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onQrTextReceived(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgDataObject) -> Unit)
    @JsName("onEvent")
    internal fun onEmojiStatusSet(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onFullscreenFailed(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit)
    @JsName("onEvent")
    internal fun onGyroscopeFailed(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit)
    @JsName("onEvent")
    internal fun onSettingsButtonClicked(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onDeactivated(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onDeviceOrientationStopped(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onFullscreenChanged(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onViewportChanged(type: String, callback: (Boolean) -> Unit)
    @JsName("onEvent")
    internal fun onMainButtonClicked(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onAccelerometerFailed(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit)
    @JsName("onEvent")
    internal fun onClipboardTextReceived(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgDataNullableObject) -> Unit)
    @JsName("onEvent")
    internal fun onFileDownloadRequested(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit)
    @JsName("onEvent")
    internal fun onContactRequested(type: String, callback: (dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit)
    @JsName("onEvent")
    internal fun onInvoiceClosed(type: String, callback: (String, String) -> Unit)
    @JsName("onEvent")
    internal fun onGyroscopeStarted(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onGyroscopeChanged(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onDeviceOrientationChanged(type: String, callback: () -> Unit)

    // End of generated part
}

val WebApp.colorScheme: ColorScheme
    get() = when (colorSchemeRaw) {
        "light" -> ColorScheme.LIGHT
        "dark" -> ColorScheme.DARK
        else -> ColorScheme.LIGHT
    }

///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType, eventHandler: EventHandler) = {
//    eventHandler(js("this").unsafeCast<WebApp>())
//}.also {
//    onEvent(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType.ViewportChanged, eventHandler: ViewportChangedEventHandler) = { it: ViewportChangedData ->
//    eventHandler(js("this").unsafeCast<WebApp>(), it)
//}.also {
//    onEventWithViewportChangedData(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType.InvoiceClosed, eventHandler: InvoiceClosedEventHandler) = { it: InvoiceClosedInfo ->
//    eventHandler(js("this").unsafeCast<WebApp>(), it)
//}.also {
//    onEventWithInvoiceClosedInfo(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType.PopupClosed, eventHandler: PopupClosedEventHandler) = { it: String? ->
//    eventHandler(js("this").unsafeCast<WebApp>(), it)
//}.also {
//    onEventWithPopupClosedInfo(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType.QRTextReceived, eventHandler: QRTextReceivedEventHandler) = { it: String ->
//    eventHandler(js("this").unsafeCast<WebApp>(), it)
//}.also {
//    onEventWithQRTextInfo(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType.ClipboardTextReceived, eventHandler: TextReceivedEventHandler) = { it: String ->
//    eventHandler(js("this").unsafeCast<WebApp>(), it)
//}.also {
//    onEventWithTextInfo(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType.WriteAccessRequested, eventHandler: WriteAccessRequestedHandler) = { it: RequestStatus ->
//    eventHandler(js("this").unsafeCast<WebApp>(), it.isAllowed)
//}.also {
//    onEventWithWriteAccessRequested(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType.ContactRequested, eventHandler: ContactRequestedHandler) = { it: RequestStatus ->
//    eventHandler(js("this").unsafeCast<WebApp>(), it.isSent)
//}.also {
//    onEventWithContactRequested(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType.SettingsButtonClicked, eventHandler: EventHandler) = {
//    eventHandler(js("this").unsafeCast<WebApp>())
//}.also {
//    onEventWithSettingsButtonClicked(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onEvent(type: EventType.ScanQRPopupClosed, eventHandler: EventHandler) = {
//    eventHandler(js("this").unsafeCast<WebApp>())
//}.also {
//    onEventWithScanQRPopupClosed(
//        type.typeName,
//        callback = it
//    )
//}
//
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onThemeChanged(eventHandler: EventHandler) = onEvent(EventType.ThemeChanged, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onMainButtonClicked(eventHandler: EventHandler) = onEvent(EventType.MainButtonClicked, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onSecondaryButtonClicked(eventHandler: EventHandler) = onEvent(EventType.SecondaryButtonClicked, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onViewportChanged(eventHandler: ViewportChangedEventHandler) = onEvent(EventType.ViewportChanged, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onBackButtonClicked(eventHandler: EventHandler) = onEvent(EventType.BackButtonClicked, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onSettingsButtonClicked(eventHandler: EventHandler) = onEvent(EventType.SettingsButtonClicked, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onInvoiceClosed(eventHandler: InvoiceClosedEventHandler) = onEvent(EventType.InvoiceClosed, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onPopupClosed(eventHandler: PopupClosedEventHandler) = onEvent(EventType.PopupClosed, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onQRTextReceived(eventHandler: QRTextReceivedEventHandler) = onEvent(EventType.QRTextReceived, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onClipboardTextReceived(eventHandler: TextReceivedEventHandler) = onEvent(EventType.ClipboardTextReceived, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onWriteAccessRequested(eventHandler: WriteAccessRequestedHandler) = onEvent(EventType.WriteAccessRequested, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onContactRequested(eventHandler: ContactRequestedHandler) = onEvent(EventType.ContactRequested, eventHandler)
///**
// * @return The callback which should be used in case you want to turn off events handling
// */
//fun WebApp.onScanQRPopupClosed(eventHandler: onScanQRPopupClosedHandler) = onEvent(EventType.ScanQRPopupClosed, eventHandler)

fun WebApp.isInitDataSafe(botToken: String) = TelegramAPIUrlsKeeper(botToken).checkWebAppData(
    initData,
    initDataUnsafe.hash
)

fun WebApp.showPopup(
    message: String,
    title: String?,
    buttons: Array<PopupButton>,
    callback: ClosePopupCallback? = null
) = showPopup(
    PopupParams(
        message,
        title,
        buttons
    ),
    callback
)

fun WebApp.showPopup(
    message: String,
    title: String?,
    firstButton: PopupButton,
    vararg otherButtons: PopupButton,
    callback: ClosePopupCallback? = null
) = showPopup(
    PopupParams(
        message,
        title,
        arrayOf(firstButton, *otherButtons)
    ),
    callback
)

var WebApp.requireClosingConfirmation
    get() = isClosingConfirmationEnabled
    set(value) {
        if (value) {
            enableClosingConfirmation()
        } else {
            disableClosingConfirmation()
        }
    }

fun WebApp.toggleClosingConfirmation() {
    requireClosingConfirmation = !requireClosingConfirmation
}
