package dev.inmo.tgbotapi.webapps.events

import dev.inmo.tgbotapi.webapps.EventType
import dev.inmo.tgbotapi.webapps.WebApp

// Part for callback typealias

typealias EmojiStatusAccessRequestedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.EmojiStatusAccessRequested, eventHandler: EmojiStatusAccessRequestedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgStatusObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onEmojiStatusAccessRequested(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEmojiStatusAccessRequested(eventHandler: EmojiStatusAccessRequestedEventHandler) = onEvent(EventType.EmojiStatusAccessRequested, eventHandler)
// Part for callback typealias

typealias AccelerometerChangedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.AccelerometerChanged, eventHandler: AccelerometerChangedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onAccelerometerChanged(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onAccelerometerChanged(eventHandler: AccelerometerChangedEventHandler) = onEvent(EventType.AccelerometerChanged, eventHandler)
// Part for callback typealias

typealias DeviceOrientationStartedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.DeviceOrientationStarted, eventHandler: DeviceOrientationStartedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onDeviceOrientationStarted(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onDeviceOrientationStarted(eventHandler: DeviceOrientationStartedEventHandler) = onEvent(EventType.DeviceOrientationStarted, eventHandler)
// Part for callback typealias

typealias EmojiStatusFailedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.EmojiStatusFailed, eventHandler: EmojiStatusFailedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgErrorObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onEmojiStatusFailed(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEmojiStatusFailed(eventHandler: EmojiStatusFailedEventHandler) = onEvent(EventType.EmojiStatusFailed, eventHandler)
// Part for callback typealias

typealias ActivatedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.Activated, eventHandler: ActivatedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onActivated(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onActivated(eventHandler: ActivatedEventHandler) = onEvent(EventType.Activated, eventHandler)
// Part for callback typealias

typealias ShareMessageSentEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.ShareMessageSent, eventHandler: ShareMessageSentEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onShareMessageSent(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onShareMessageSent(eventHandler: ShareMessageSentEventHandler) = onEvent(EventType.ShareMessageSent, eventHandler)
// Part for callback typealias

typealias LocationManagerUpdatedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.LocationManagerUpdated, eventHandler: LocationManagerUpdatedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onLocationManagerUpdated(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onLocationManagerUpdated(eventHandler: LocationManagerUpdatedEventHandler) = onEvent(EventType.LocationManagerUpdated, eventHandler)
// Part for callback typealias

typealias BiometricTokenUpdatedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgIsUpdatedObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.BiometricTokenUpdated, eventHandler: BiometricTokenUpdatedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgIsUpdatedObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onBiometricTokenUpdated(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onBiometricTokenUpdated(eventHandler: BiometricTokenUpdatedEventHandler) = onEvent(EventType.BiometricTokenUpdated, eventHandler)
// Part for callback typealias

typealias DeviceOrientationFailedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.DeviceOrientationFailed, eventHandler: DeviceOrientationFailedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgErrorObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onDeviceOrientationFailed(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onDeviceOrientationFailed(eventHandler: DeviceOrientationFailedEventHandler) = onEvent(EventType.DeviceOrientationFailed, eventHandler)
// Part for callback typealias

typealias SafeAreaChangedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.SafeAreaChanged, eventHandler: SafeAreaChangedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onSafeAreaChanged(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onSafeAreaChanged(eventHandler: SafeAreaChangedEventHandler) = onEvent(EventType.SafeAreaChanged, eventHandler)
// Part for callback typealias

typealias WriteAccessRequestedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.WriteAccessRequested, eventHandler: WriteAccessRequestedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgStatusObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onWriteAccessRequested(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onWriteAccessRequested(eventHandler: WriteAccessRequestedEventHandler) = onEvent(EventType.WriteAccessRequested, eventHandler)
// Part for callback typealias

typealias ContentSafeAreaChangedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.ContentSafeAreaChanged, eventHandler: ContentSafeAreaChangedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onContentSafeAreaChanged(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onContentSafeAreaChanged(eventHandler: ContentSafeAreaChangedEventHandler) = onEvent(EventType.ContentSafeAreaChanged, eventHandler)
// Part for callback typealias

typealias AccelerometerStartedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.AccelerometerStarted, eventHandler: AccelerometerStartedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onAccelerometerStarted(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onAccelerometerStarted(eventHandler: AccelerometerStartedEventHandler) = onEvent(EventType.AccelerometerStarted, eventHandler)
// Part for callback typealias

typealias AccelerometerStoppedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.AccelerometerStopped, eventHandler: AccelerometerStoppedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onAccelerometerStopped(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onAccelerometerStopped(eventHandler: AccelerometerStoppedEventHandler) = onEvent(EventType.AccelerometerStopped, eventHandler)
// Part for callback typealias

typealias PopupClosedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.popup.PopupClosedEventArg) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.PopupClosed, eventHandler: PopupClosedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.popup.PopupClosedEventArg ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onPopupClosed(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onPopupClosed(eventHandler: PopupClosedEventHandler) = onEvent(EventType.PopupClosed, eventHandler)
// Part for callback typealias

typealias GyroscopeStoppedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.GyroscopeStopped, eventHandler: GyroscopeStoppedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onGyroscopeStopped(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onGyroscopeStopped(eventHandler: GyroscopeStoppedEventHandler) = onEvent(EventType.GyroscopeStopped, eventHandler)
// Part for callback typealias

typealias BackButtonClickedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.BackButtonClicked, eventHandler: BackButtonClickedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onBackButtonClicked(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onBackButtonClicked(eventHandler: BackButtonClickedEventHandler) = onEvent(EventType.BackButtonClicked, eventHandler)
// Part for callback typealias

typealias HomeScreenAddedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.HomeScreenAdded, eventHandler: HomeScreenAddedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onHomeScreenAdded(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onHomeScreenAdded(eventHandler: HomeScreenAddedEventHandler) = onEvent(EventType.HomeScreenAdded, eventHandler)
// Part for callback typealias

typealias ShareMessageFailedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.ShareMessageFailed, eventHandler: ShareMessageFailedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgErrorObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onShareMessageFailed(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onShareMessageFailed(eventHandler: ShareMessageFailedEventHandler) = onEvent(EventType.ShareMessageFailed, eventHandler)
// Part for callback typealias

typealias ThemeChangedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.ThemeChanged, eventHandler: ThemeChangedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onThemeChanged(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onThemeChanged(eventHandler: ThemeChangedEventHandler) = onEvent(EventType.ThemeChanged, eventHandler)
// Part for callback typealias

typealias BiometricManagerUpdatedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.BiometricManagerUpdated, eventHandler: BiometricManagerUpdatedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onBiometricManagerUpdated(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onBiometricManagerUpdated(eventHandler: BiometricManagerUpdatedEventHandler) = onEvent(EventType.BiometricManagerUpdated, eventHandler)
// Part for callback typealias

typealias ScanQrPopupClosedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.ScanQrPopupClosed, eventHandler: ScanQrPopupClosedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onScanQrPopupClosed(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onScanQrPopupClosed(eventHandler: ScanQrPopupClosedEventHandler) = onEvent(EventType.ScanQrPopupClosed, eventHandler)
// Part for callback typealias

typealias BiometricAuthRequestedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgBiometricAuthRequested) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.BiometricAuthRequested, eventHandler: BiometricAuthRequestedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgBiometricAuthRequested ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onBiometricAuthRequested(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onBiometricAuthRequested(eventHandler: BiometricAuthRequestedEventHandler) = onEvent(EventType.BiometricAuthRequested, eventHandler)
// Part for callback typealias

typealias HomeScreenCheckedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.HomeScreenChecked, eventHandler: HomeScreenCheckedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgStatusObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onHomeScreenChecked(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onHomeScreenChecked(eventHandler: HomeScreenCheckedEventHandler) = onEvent(EventType.HomeScreenChecked, eventHandler)
// Part for callback typealias

typealias LocationRequestedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgLocationDataObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.LocationRequested, eventHandler: LocationRequestedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgLocationDataObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onLocationRequested(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onLocationRequested(eventHandler: LocationRequestedEventHandler) = onEvent(EventType.LocationRequested, eventHandler)
// Part for callback typealias

typealias SecondaryButtonClickedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.SecondaryButtonClicked, eventHandler: SecondaryButtonClickedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onSecondaryButtonClicked(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onSecondaryButtonClicked(eventHandler: SecondaryButtonClickedEventHandler) = onEvent(EventType.SecondaryButtonClicked, eventHandler)
// Part for callback typealias

typealias QrTextReceivedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgDataObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.QrTextReceived, eventHandler: QrTextReceivedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgDataObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onQrTextReceived(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onQrTextReceived(eventHandler: QrTextReceivedEventHandler) = onEvent(EventType.QrTextReceived, eventHandler)
// Part for callback typealias

typealias EmojiStatusSetEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.EmojiStatusSet, eventHandler: EmojiStatusSetEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onEmojiStatusSet(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEmojiStatusSet(eventHandler: EmojiStatusSetEventHandler) = onEvent(EventType.EmojiStatusSet, eventHandler)
// Part for callback typealias

typealias FullscreenFailedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.FullscreenFailed, eventHandler: FullscreenFailedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgErrorObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onFullscreenFailed(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onFullscreenFailed(eventHandler: FullscreenFailedEventHandler) = onEvent(EventType.FullscreenFailed, eventHandler)
// Part for callback typealias

typealias GyroscopeFailedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.GyroscopeFailed, eventHandler: GyroscopeFailedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgErrorObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onGyroscopeFailed(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onGyroscopeFailed(eventHandler: GyroscopeFailedEventHandler) = onEvent(EventType.GyroscopeFailed, eventHandler)
// Part for callback typealias

typealias SettingsButtonClickedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.SettingsButtonClicked, eventHandler: SettingsButtonClickedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onSettingsButtonClicked(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onSettingsButtonClicked(eventHandler: SettingsButtonClickedEventHandler) = onEvent(EventType.SettingsButtonClicked, eventHandler)
// Part for callback typealias

typealias DeactivatedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.Deactivated, eventHandler: DeactivatedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onDeactivated(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onDeactivated(eventHandler: DeactivatedEventHandler) = onEvent(EventType.Deactivated, eventHandler)
// Part for callback typealias

typealias DeviceOrientationStoppedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.DeviceOrientationStopped, eventHandler: DeviceOrientationStoppedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onDeviceOrientationStopped(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onDeviceOrientationStopped(eventHandler: DeviceOrientationStoppedEventHandler) = onEvent(EventType.DeviceOrientationStopped, eventHandler)
// Part for callback typealias

typealias FullscreenChangedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.FullscreenChanged, eventHandler: FullscreenChangedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onFullscreenChanged(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onFullscreenChanged(eventHandler: FullscreenChangedEventHandler) = onEvent(EventType.FullscreenChanged, eventHandler)
// Part for callback typealias

typealias ViewportChangedEventHandler = WebApp.(Boolean) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.ViewportChanged, eventHandler: ViewportChangedEventHandler) = { p0: Boolean ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onViewportChanged(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onViewportChanged(eventHandler: ViewportChangedEventHandler) = onEvent(EventType.ViewportChanged, eventHandler)
// Part for callback typealias

typealias MainButtonClickedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.MainButtonClicked, eventHandler: MainButtonClickedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onMainButtonClicked(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onMainButtonClicked(eventHandler: MainButtonClickedEventHandler) = onEvent(EventType.MainButtonClicked, eventHandler)
// Part for callback typealias

typealias AccelerometerFailedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgErrorObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.AccelerometerFailed, eventHandler: AccelerometerFailedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgErrorObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onAccelerometerFailed(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onAccelerometerFailed(eventHandler: AccelerometerFailedEventHandler) = onEvent(EventType.AccelerometerFailed, eventHandler)
// Part for callback typealias

typealias ClipboardTextReceivedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgDataNullableObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.ClipboardTextReceived, eventHandler: ClipboardTextReceivedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgDataNullableObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onClipboardTextReceived(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onClipboardTextReceived(eventHandler: ClipboardTextReceivedEventHandler) = onEvent(EventType.ClipboardTextReceived, eventHandler)
// Part for callback typealias

typealias FileDownloadRequestedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.FileDownloadRequested, eventHandler: FileDownloadRequestedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgStatusObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onFileDownloadRequested(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onFileDownloadRequested(eventHandler: FileDownloadRequestedEventHandler) = onEvent(EventType.FileDownloadRequested, eventHandler)
// Part for callback typealias

typealias ContactRequestedEventHandler = WebApp.(dev.inmo.tgbotapi.webapps.args.ArgStatusObject) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.ContactRequested, eventHandler: ContactRequestedEventHandler) = { p0: dev.inmo.tgbotapi.webapps.args.ArgStatusObject ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0)
}.also {
    onContactRequested(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onContactRequested(eventHandler: ContactRequestedEventHandler) = onEvent(EventType.ContactRequested, eventHandler)
// Part for callback typealias

typealias InvoiceClosedEventHandler = WebApp.(String, String) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.InvoiceClosed, eventHandler: InvoiceClosedEventHandler) = { p0: String, p1: String ->
    eventHandler(js("this").unsafeCast<WebApp>(), p0, p1)
}.also {
    onInvoiceClosed(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onInvoiceClosed(eventHandler: InvoiceClosedEventHandler) = onEvent(EventType.InvoiceClosed, eventHandler)
// Part for callback typealias

typealias GyroscopeStartedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.GyroscopeStarted, eventHandler: GyroscopeStartedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onGyroscopeStarted(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onGyroscopeStarted(eventHandler: GyroscopeStartedEventHandler) = onEvent(EventType.GyroscopeStarted, eventHandler)
// Part for callback typealias

typealias GyroscopeChangedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.GyroscopeChanged, eventHandler: GyroscopeChangedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onGyroscopeChanged(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onGyroscopeChanged(eventHandler: GyroscopeChangedEventHandler) = onEvent(EventType.GyroscopeChanged, eventHandler)
// Part for callback typealias

typealias DeviceOrientationChangedEventHandler = WebApp.() -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.DeviceOrientationChanged, eventHandler: DeviceOrientationChangedEventHandler) = {  ->
    eventHandler(js("this").unsafeCast<WebApp>(), )
}.also {
    onDeviceOrientationChanged(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onDeviceOrientationChanged(eventHandler: DeviceOrientationChangedEventHandler) = onEvent(EventType.DeviceOrientationChanged, eventHandler)
