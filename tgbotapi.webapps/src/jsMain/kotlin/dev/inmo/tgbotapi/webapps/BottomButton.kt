package dev.inmo.tgbotapi.webapps

import kotlin.js.Json
import kotlin.js.json

external class BottomButton {
    val text: String

    fun setText(text: String): BottomButton

    var color: String
    var textColor: String

    val isVisible: Boolean

    fun show(): BottomButton

    fun hide(): BottomButton

    val isActive: Boolean

    fun enable(): BottomButton

    fun disable(): BottomButton

    val isProgressVisible: Boolean

    fun showProgress(leaveActive: Boolean = definedExternally): BottomButton

    fun hideProgress(): BottomButton

    /**
     * **This method argument do not accept `this` [WebApp] object**
     */
    fun onClick(eventHandler: () -> Unit): BottomButton

    fun offClick(eventHandler: () -> Unit): BottomButton

    internal fun setParams(params: Json): BottomButton
}

data class BottomButtonParams(
    val text: String? = null,
    val color: String? = null,
    val textColor: String? = null,
    val isActive: Boolean? = null,
    val isVisible: Boolean? = null,
)

fun BottomButton.setParams(params: BottomButtonParams) = setParams(
    json(
        *listOfNotNull(
            params.text ?.let { "text" to params.text },
            params.color ?.let { "color" to params.color },
            params.textColor ?.let { "text_color" to params.textColor },
            params.isActive ?.let { "is_active" to params.isActive },
            params.isVisible ?.let { "is_visible" to params.isVisible },
        ).toTypedArray(),
    ),
)

@Deprecated("Renamed in telegram api", ReplaceWith("BottomButton", "dev.inmo.tgbotapi.webapps.BottomButton"))
typealias MainButton = BottomButton
