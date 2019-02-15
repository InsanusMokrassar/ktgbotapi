package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import kotlinx.serialization.*

@Serializable
data class KeyboardButton(
    val text: String,
    @SerialName("request_contact")
    @Optional
    val requestContact: Boolean? = null,
    @SerialName("request_location")
    @Optional
    val requestLocation: Boolean? = null
)
