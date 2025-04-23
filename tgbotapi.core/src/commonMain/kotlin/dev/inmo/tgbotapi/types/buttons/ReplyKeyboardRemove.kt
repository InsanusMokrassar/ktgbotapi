package dev.inmo.tgbotapi.types.buttons

import kotlinx.serialization.*

@Serializable
data class ReplyKeyboardRemove(
    val selective: Boolean? = null,
) : KeyboardMarkup {
    @SerialName("remove_keyboard")
    @Required
    @EncodeDefault
    val removeKeyboard: Boolean = true
}
