package dev.inmo.tgbotapi.types.location

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationAddress(
    @SerialName(countryCodeField)
    val countryCode: String,
    @SerialName(stateField)
    val state: String,
    @SerialName(cityField)
    val city: String,
    @SerialName(streetField)
    val street: String,
)
