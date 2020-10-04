package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.CommonAbstracts.Locationed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(latitudeField)
    override val latitude: Double
) : Locationed
