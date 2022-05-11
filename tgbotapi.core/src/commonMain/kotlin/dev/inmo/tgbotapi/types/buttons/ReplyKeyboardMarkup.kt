package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplyKeyboardMarkup(
    val keyboard: Matrix<KeyboardButton>,
    @SerialName(resizeKeyboardField)
    val resizeKeyboard: Boolean? = null,
    @SerialName(oneTimeKeyboardField)
    val oneTimeKeyboard: Boolean? = null,
    @SerialName(inputFieldPlaceholderField)
    val inputFieldPlaceholder: String? = null,
    val selective: Boolean? = null
) : KeyboardMarkup {
    init {
        if (inputFieldPlaceholder != null && inputFieldPlaceholder.length !in inputFieldPlaceholderLimit) {
            error("Field $inputFieldPlaceholderField length must be in $inputFieldPlaceholderLimit, but was ${inputFieldPlaceholder.length}")
        }
    }
}
