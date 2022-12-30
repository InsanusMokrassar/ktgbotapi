package dev.inmo.tgbotapi.webapps

import kotlin.js.json

external interface OpenLinkParams {
    @JsName("try_instant_view")
    val tryInstantView: Boolean
}

fun OpenLinkParams(
    tryInstantView: Boolean
) = json(
    *listOfNotNull(
        "try_instant_view" to tryInstantView
    ).toTypedArray()
).unsafeCast<OpenLinkParams>()
