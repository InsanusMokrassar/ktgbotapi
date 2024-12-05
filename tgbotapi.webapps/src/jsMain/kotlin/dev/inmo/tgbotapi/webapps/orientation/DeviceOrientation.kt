package dev.inmo.tgbotapi.webapps.orientation

external interface DeviceOrientation {
    val isStarted: Boolean
    val absolute: Boolean
    val alpha: Double
    val beta: Double
    val gamma: Double

    fun start(params: DeviceOrientationStartParams, callback: (Boolean) -> Unit)
    fun stop(callback: (Boolean) -> Unit)
}

