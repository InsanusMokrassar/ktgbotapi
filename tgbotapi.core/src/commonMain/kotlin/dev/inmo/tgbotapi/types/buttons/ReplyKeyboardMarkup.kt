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
    val selective: Boolean? = null,
    @SerialName(isPersistentField)
    val persistent: Boolean? = null
) : KeyboardMarkup {
    init {
        if (inputFieldPlaceholder != null && inputFieldPlaceholder.length !in inputFieldPlaceholderLimit) {
            error("Field $inputFieldPlaceholderField length must be in $inputFieldPlaceholderLimit, but was ${inputFieldPlaceholder.length}")
        }
    }

    fun add(other: ReplyKeyboardMarkup, placeholderDelimiter: String = "\n"): ReplyKeyboardMarkup {
        return ReplyKeyboardMarkup(
            keyboard = keyboard + other.keyboard,
            resizeKeyboard = resizeKeyboard ?.or(other.resizeKeyboard ?: false) ?: other.resizeKeyboard,
            oneTimeKeyboard = oneTimeKeyboard ?.or(other.oneTimeKeyboard ?: false) ?: other.oneTimeKeyboard,
            inputFieldPlaceholder = inputFieldPlaceholder ?.plus(other.inputFieldPlaceholder ?.let { placeholderDelimiter + it } ?: "") ?: other.inputFieldPlaceholder,
            selective = selective ?.or(other.selective ?: false) ?: other.selective,
            persistent = persistent ?.or(other.persistent ?: false) ?: other.persistent,
        )
    }

    operator fun plus(other: ReplyKeyboardMarkup): ReplyKeyboardMarkup {
        return add(other)
    }

    operator fun minus(other: ReplyKeyboardMarkup): ReplyKeyboardMarkup {
        val otherButtons = other.keyboard.flatten()
        return copy(
            keyboard.mapNotNull { row ->
                row.filter { button ->
                    button !in otherButtons
                }.takeIf {
                    it.isNotEmpty()
                }
            }
        )
    }
}
