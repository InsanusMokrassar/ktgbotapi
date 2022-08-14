package dev.inmo.tgbotapi.webapps.popup

external class PopupButton(
    id: String,
    type: String,
    text: String? = definedExternally
) {
    val id: String
    val type: String
    val text: String?
}

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
) = PopupButton(id, PopupButtonType.Default.typeName, text)

fun OkPopupButton(
    id: String
) = PopupButton(id, PopupButtonType.Ok.typeName)

fun ClosePopupButton(
    id: String
) = PopupButton(id, PopupButtonType.Close.typeName)

fun CancelPopupButton(
    id: String
) = PopupButton(id, PopupButtonType.Cancel.typeName)

fun DestructivePopupButton(
    id: String,
    text: String
) = PopupButton(id, PopupButtonType.Destructive.typeName, text)
