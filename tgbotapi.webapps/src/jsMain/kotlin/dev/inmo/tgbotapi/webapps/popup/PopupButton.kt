@file:Suppress("unused", "INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING")

package dev.inmo.tgbotapi.webapps.popup

import kotlin.js.json

external interface PopupButton {
    val id: String
    val type: PopupButtonType
    val text: String?
}

fun PopupButton(
    id: String,
    type: PopupButtonType,
    text: String? = null
) = json(
    *listOfNotNull(
        "id" to id,
        "type" to type.typeName,
        ("text" to text).takeIf { text != null }
    ).toTypedArray()
).unsafeCast<PopupButton>()

value class PopupButtonType(
    val typeName: String
) {
    companion object {
        val Default = PopupButtonType("default")
        val Ok = PopupButtonType("ok")
        val Close = PopupButtonType("close")
        val Cancel = PopupButtonType("cancel")
        val Destructive = PopupButtonType("destructive")
    }
}

fun DefaultPopupButton(
    id: String,
    text: String
) = PopupButton(id, PopupButtonType.Default, text)

fun OkPopupButton(
    id: String
) = PopupButton(id, PopupButtonType.Ok)

fun ClosePopupButton(
    id: String
) = PopupButton(id, PopupButtonType.Close)

fun CancelPopupButton(
    id: String
) = PopupButton(id, PopupButtonType.Cancel)

fun DestructivePopupButton(
    id: String,
    text: String
) = PopupButton(id, PopupButtonType.Destructive, text)
