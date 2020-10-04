package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val state: String = "",
    @SerialName(postCodeField)
    val postCode: String = ""
)
