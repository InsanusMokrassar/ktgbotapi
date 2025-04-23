package dev.inmo.tgbotapi.webapps.gyroscope

external interface Gyroscope {
    val isStarted: Boolean
    val x: Double
    val y: Double
    val z: Double

    fun start(
        params: GyroscopeStartParams,
        callback: (Boolean) -> Unit = definedExternally,
    )

    fun stop(callback: (Boolean) -> Unit)
}
