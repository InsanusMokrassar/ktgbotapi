package dev.inmo.tgbotapi.webapps.popup

external class PopupParams(
    message: String,
    title: String?,
    buttons: Array<PopupButton>
) {
    val title: String?
}

fun PopupParams(
    message: String,
    firstButton: PopupButton,
    vararg otherButtons: PopupButton
) = PopupParams(
    message,
    null,
    arrayOf(
        firstButton,
        *otherButtons
    )
)

fun PopupParams(
    title: String,
    message: String,
    firstButton: PopupButton,
    vararg otherButtons: PopupButton
) = PopupParams(
    message,
    title,
    arrayOf(
        firstButton,
        *otherButtons
    )
)
