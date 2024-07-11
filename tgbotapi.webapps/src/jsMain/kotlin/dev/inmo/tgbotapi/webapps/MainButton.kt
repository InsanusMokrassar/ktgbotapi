package dev.inmo.tgbotapi.webapps

import kotlin.js.Json
import kotlin.js.json

external class MainButton {
    val text: String
    fun setText(text: String): MainButton

    var color: String
    var textColor: String

    val isVisible: Boolean
    fun show(): MainButton
    fun hide(): MainButton

    val isActive: Boolean
    fun enable(): MainButton
    fun disable(): MainButton

    val isProgressVisible: Boolean
    fun showProgress(leaveActive: Boolean = definedExternally): MainButton
    fun hideProgress(): MainButton

    fun onClick(eventHandler: () -> Unit): MainButton
    fun offClick(eventHandler: () -> Unit): MainButton

    internal fun setParams(params: Json): MainButton
}

data class MainButtonParams(
    val text: String? = null,
    val color: String? = null,
    val textColor: String? = null,
    val isActive: Boolean? = null,
    val isVisible: Boolean? = null
)

@Deprecated(message="Use onClick without EventHandler")
fun MainButton.onClick(eventHandler: EventHandler) = onClick {
    val that = js("this").unsafeCast<WebApp>()
    that.eventHandler()
}

fun MainButton.setParams(params: MainButtonParams) = setParams(
    json(
        *listOfNotNull(
            params.text ?.let { "text" to params.text },
            params.color ?.let { "color" to params.color },
            params.textColor ?.let { "text_color" to params.textColor },
            params.isActive ?.let { "is_active" to params.isActive },
            params.isVisible ?.let { "is_visible" to params.isVisible },
        ).toTypedArray()
    )
)


