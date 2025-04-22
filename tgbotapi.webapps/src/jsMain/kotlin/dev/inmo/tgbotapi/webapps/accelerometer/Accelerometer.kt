package dev.inmo.tgbotapi.webapps.accelerometer

external interface Accelerometer {
    val isStarted: Boolean
    val x: Double
    val y: Double
    val z: Double

    fun start(
        params: AccelerometerStartParams,
        callback: (Boolean) -> Unit = definedExternally,
    )

    fun stop(callback: (Boolean) -> Unit)
}
