package dev.inmo.tgbotapi.webapps.location

external interface LocationManager {
    val isInited: Boolean

    val isLocationAvailable: Boolean
    val isAccessRequested: Boolean
    val isAccessGranted: Boolean

    fun init(callback: () -> Unit = definedExternally)

    fun getLocation(callback: (location: LocationData) -> Unit)

    fun openSettings()
}