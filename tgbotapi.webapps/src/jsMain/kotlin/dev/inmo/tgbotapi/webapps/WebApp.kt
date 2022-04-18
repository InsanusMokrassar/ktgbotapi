package dev.inmo.tgbotapi.webapps

external class WebApp {
    val initData: String
    val initDataUnsafe: WebAppInitData

    @JsName("colorScheme")
    val colorSchemeRaw: String
    val themeParams: ThemeParams

    val isExpanded: Boolean
    val viewportHeight: Float
    val viewportStableHeight: Float

    @JsName("MainButton")
    val mainButton: MainButton

    internal fun onEvent(type: String, callback: () -> Unit)
    @JsName("onEvent")
    internal fun onEventWithBoolean(type: String, callback: (Boolean) -> Unit)

    fun offEvent(type: String, callback: () -> Unit)
    @JsName("offEvent")
    fun offEventWithBoolean(type: String, callback: (Boolean) -> Unit)

    fun sendData(data: String)

    fun ready()
    fun expand()
    fun close()
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
fun WebApp.onEvent(type: EventType.ViewportChanged, eventHandler: ViewportChangedEventHandler) = { it: Boolean ->
    eventHandler(js("this").unsafeCast<WebApp>(), it)
}.also {
    onEventWithBoolean(
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
