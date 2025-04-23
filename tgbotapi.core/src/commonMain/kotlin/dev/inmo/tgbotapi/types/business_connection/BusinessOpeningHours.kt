package dev.inmo.tgbotapi.types.business_connection

import dev.inmo.tgbotapi.types.openingHoursField
import dev.inmo.tgbotapi.types.timeZoneNameField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusinessOpeningHours(
    @SerialName(timeZoneNameField)
    val timeZoneName: String,
    @SerialName(openingHoursField)
    val openingHours: List<BusinessOpeningHoursInterval>,
)
