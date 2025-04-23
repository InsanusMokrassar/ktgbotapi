package dev.inmo.tgbotapi.webapps.popup

import kotlin.js.json

external interface PopupParams {
    val message: String
    val title: String?
    val buttons: Array<PopupButton>
}

fun PopupParams(
    message: String,
    title: String?,
    buttons: Array<PopupButton>,
) = json(
    *listOfNotNull(
        "message" to message,
        "buttons" to buttons,
        ("title" to title).takeIf { title != null },
    ).toTypedArray(),
).unsafeCast<PopupParams>()

fun PopupParams(
    message: String,
    firstButton: PopupButton,
    vararg otherButtons: PopupButton,
) = PopupParams(
    message,
    null,
    arrayOf(
        firstButton,
        *otherButtons,
    ),
)

fun PopupParams(
    title: String,
    message: String,
    firstButton: PopupButton,
    vararg otherButtons: PopupButton,
) = PopupParams(
    message,
    title,
    arrayOf(
        firstButton,
        *otherButtons,
    ),
)
