package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import kotlinx.serialization.*

@Serializable
data class ReplyKeyboardRemove(
    @Optional
    val selective: Boolean? = null
) : KeyboardMarkup {
    @SerialName("remove_keyboard")
    val removeKeyboard: Boolean = true
}
