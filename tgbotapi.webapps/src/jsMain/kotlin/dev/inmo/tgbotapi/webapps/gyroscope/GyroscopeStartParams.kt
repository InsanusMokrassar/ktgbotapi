package dev.inmo.tgbotapi.webapps.gyroscope

import dev.inmo.tgbotapi.types.MilliSeconds
import kotlin.js.json

external interface GyroscopeStartParams {
    @JsName("refresh_rate")
    val refreshRate: MilliSeconds
}

fun GyroscopeStartParams(refreshRate: MilliSeconds = 1000): GyroscopeStartParams = json(
    "refresh_rate" to refreshRate,
).unsafeCast<GyroscopeStartParams>()
