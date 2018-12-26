package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import kotlinx.serialization.*

@Serializable
data class ReplyKeyboardMarkup(
    val keyboard: Matrix<KeyboardButton>,
    @SerialName("resize_keyboard")
    @Optional
    val resizeKeyboard: Boolean? = null,
    @SerialName("one_time_keyboard")
    @Optional
    val oneTimeKeyboard: Boolean? = null,
    @Optional
    val selective: Boolean? = null
) : KeyboardMarkup
