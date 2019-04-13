package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import kotlinx.serialization.*

@Serializable
data class KeyboardButton(
    val text: String,
    @SerialName("request_contact")
    val requestContact: Boolean? = null,
    @SerialName("request_location")
    val requestLocation: Boolean? = null
)
