package dev.inmo.tgbotapi.webapps.popup

import kotlin.js.json

external interface ScanQrPopupParams {
    val text: String?
}

fun ScanQrPopupParams(text: String? = null) = json(
    *listOfNotNull(
        ("text" to text).takeIf { text != null },
    ).toTypedArray(),
).unsafeCast<ScanQrPopupParams>()
