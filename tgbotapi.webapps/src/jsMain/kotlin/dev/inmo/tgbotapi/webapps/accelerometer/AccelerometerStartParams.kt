package dev.inmo.tgbotapi.webapps.accelerometer

import dev.inmo.tgbotapi.types.MilliSeconds
import kotlin.js.json

external interface AccelerometerStartParams {
    @JsName("refresh_rate")
    val refreshRate: MilliSeconds
}

fun AccelerometerStartParams(refreshRate: MilliSeconds = 1000): AccelerometerStartParams =
    json(
        "refresh_rate" to refreshRate,
    ).unsafeCast<AccelerometerStartParams>()
