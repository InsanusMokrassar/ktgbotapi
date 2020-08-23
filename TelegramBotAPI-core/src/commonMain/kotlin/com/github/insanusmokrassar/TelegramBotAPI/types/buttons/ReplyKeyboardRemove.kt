package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplyKeyboardRemove(
    val selective: Boolean? = null
) : KeyboardMarkup {
    @SerialName("remove_keyboard")
    val removeKeyboard: Boolean = true
}
