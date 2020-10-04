package dev.inmo.tgbotapi.types.buttons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplyKeyboardMarkup(
    val keyboard: Matrix<KeyboardButton>,
    @SerialName("resize_keyboard")
    val resizeKeyboard: Boolean? = null,
    @SerialName("one_time_keyboard")
    val oneTimeKeyboard: Boolean? = null,
    val selective: Boolean? = null
) : KeyboardMarkup
