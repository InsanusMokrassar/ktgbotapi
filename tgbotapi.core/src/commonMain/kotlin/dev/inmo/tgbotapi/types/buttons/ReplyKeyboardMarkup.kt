package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.inputFieldPlaceholderField
import dev.inmo.tgbotapi.types.inputFieldPlaceholderLimit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplyKeyboardMarkup(
    val keyboard: Matrix<KeyboardButton>,
    @SerialName("resize_keyboard")
    val resizeKeyboard: Boolean? = null,
    @SerialName("one_time_keyboard")
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
