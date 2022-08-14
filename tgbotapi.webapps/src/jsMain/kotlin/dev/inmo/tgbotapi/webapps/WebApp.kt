package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.webapps.haptic.HapticFeedback
import dev.inmo.tgbotapi.webapps.invoice.InvoiceClosedInfo
import dev.inmo.tgbotapi.webapps.popup.ClosePopupCallback
import dev.inmo.tgbotapi.webapps.popup.PopupParams

external class WebApp {
    val version: String

    val initData: String
    val initDataUnsafe: WebAppInitData

    val headerColor: HEXColor?
    fun setHeaderColor(color: Color.BackgroundColor)
    val backgroundColor: HEXColor?
    fun setBackgroundColor(color: Color.Hex)
    fun setBackgroundColor(color: Color.BackgroundColor)

    @JsName("colorScheme")
    val colorSchemeRaw: String
    val themeParams: ThemeParams

    val isExpanded: Boolean
    val viewportHeight: Float
    val viewportStableHeight: Float


    val isClosingConfirmationEnabled: Boolean
    fun enableClosingConfirmation()
    fun disableClosingConfirmation()

    fun showPopup(params: PopupParams, callback: ClosePopupCallback? = definedExternally)
    fun showAlert(message: String, callback: AlertCallback? = definedExternally)
    fun showConfirm(message: String, callback: ConfirmCallback? = definedExternally)

    @JsName("MainButton")
    val mainButton: MainButton

    @JsName("BackButton")
    val backButton: BackButton

    @JsName("HapticFeedback")
    val hapticFeedback: HapticFeedback

    internal fun onEvent(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onEventWithViewportChangedData(type: String, callback: (ViewportChangedData) -> Unit)
    @JsName("onEvent")
    internal fun onEventWithInvoiceClosedInfo(type: String, callback: (InvoiceClosedInfo) -> Unit)
    @JsName("onEvent")
    internal fun onEventWithPopupClosedInfo(type: String, callback: (String?) -> Unit)

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
}

val WebApp.colorScheme: ColorScheme
    get() = when (colorSchemeRaw) {
        "light" -> ColorScheme.LIGHT
        "dark" -> ColorScheme.DARK
        else -> ColorScheme.LIGHT
    }

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType, eventHandler: EventHandler) = {
    eventHandler(js("this").unsafeCast<WebApp>())
}.also {
    onEvent(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.ViewportChanged, eventHandler: ViewportChangedEventHandler) = { it: ViewportChangedData ->
    eventHandler(js("this").unsafeCast<WebApp>(), it)
}.also {
    onEventWithViewportChangedData(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.InvoiceClosed, eventHandler: InvoiceClosedEventHandler) = { it: InvoiceClosedInfo ->
    eventHandler(js("this").unsafeCast<WebApp>(), it)
}.also {
    onEventWithInvoiceClosedInfo(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.PopupClosed, eventHandler: PopupClosedEventHandler) = { it: String? ->
    eventHandler(js("this").unsafeCast<WebApp>(), it)
}.also {
    onEventWithPopupClosedInfo(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onThemeChanged(eventHandler: EventHandler) = onEvent(EventType.ThemeChanged, eventHandler)
/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onMainButtonClicked(eventHandler: EventHandler) = onEvent(EventType.MainButtonClicked, eventHandler)
/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onViewportChanged(eventHandler: ViewportChangedEventHandler) = onEvent(EventType.ViewportChanged, eventHandler)
/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onBackButtonClicked(eventHandler: EventHandler) = onEvent(EventType.BackButtonClicked, eventHandler)
/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onSettingsButtonClicked(eventHandler: EventHandler) = onEvent(EventType.SettingsButtonClicked, eventHandler)
/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onInvoiceClosed(eventHandler: InvoiceClosedEventHandler) = onEvent(EventType.InvoiceClosed, eventHandler)
/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onPopupClosed(eventHandler: PopupClosedEventHandler) = onEvent(EventType.PopupClosed, eventHandler)

fun WebApp.isInitDataSafe(botToken: String) = TelegramAPIUrlsKeeper(botToken).checkWebAppData(
    initData,
    initDataUnsafe.hash
)
