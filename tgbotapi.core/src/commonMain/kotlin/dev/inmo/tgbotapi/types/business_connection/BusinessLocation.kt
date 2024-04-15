package dev.inmo.tgbotapi.types.business_connection

import dev.inmo.tgbotapi.types.addressField
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.locationField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusinessLocation(
    @SerialName(addressField)
    val address: String,
    @SerialName(locationField)
    val location: StaticLocation
)
