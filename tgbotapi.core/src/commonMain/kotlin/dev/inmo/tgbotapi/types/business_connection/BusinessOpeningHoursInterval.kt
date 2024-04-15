package dev.inmo.tgbotapi.types.business_connection

import dev.inmo.tgbotapi.types.closingMinuteField
import dev.inmo.tgbotapi.types.openingMinuteField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusinessOpeningHoursInterval(
    @SerialName(openingMinuteField)
    val opening: MinutesInterval,
    @SerialName(closingMinuteField)
    val closing: MinutesInterval
)
