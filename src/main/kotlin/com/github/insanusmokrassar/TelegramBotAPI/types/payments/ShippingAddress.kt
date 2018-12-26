package com.github.insanusmokrassar.TelegramBotAPI.types.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

@Serializable
data class ShippingAddress(
    @SerialName(countryCodeField)
    val countryCode: String,
    @SerialName(cityField)
    val city: String,
    @SerialName(firstStreetLineField)
    val firstStreetLine: String,
    @SerialName(secondStreetLineField)
    val secondStreetLine: String,
    @SerialName(stateField)
    @Optional
    val state: String = "",
    @SerialName(postCodeField)
    @Optional
    val postCode: String = ""
)
