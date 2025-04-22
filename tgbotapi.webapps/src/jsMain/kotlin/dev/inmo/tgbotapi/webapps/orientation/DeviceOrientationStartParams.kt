package dev.inmo.tgbotapi.webapps.orientation

import dev.inmo.tgbotapi.types.MilliSeconds
import kotlin.js.json

external interface DeviceOrientationStartParams {
    @JsName("refresh_rate")
    val refreshRate: MilliSeconds

    @JsName("need_absolute")
    val needAbsolute: Boolean
}

fun DeviceOrientationStartParams(
    refreshRate: MilliSeconds = 1000,
    needAbsolute: Boolean = false,
): DeviceOrientationStartParams =
    json(
        "refresh_rate" to refreshRate,
        "need_absolute" to needAbsolute,
    ).unsafeCast<DeviceOrientationStartParams>()
