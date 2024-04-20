package dev.inmo.tgbotapi.types

import korlibs.time.Date
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Birthdate(
    @SerialName(dayField)
    val day: Int,
    @SerialName(monthField)
    val month: Int,
    @SerialName(yearField)
    val year: Int
) {
    val date: Date by lazy {
        Date(year, month, day)
    }
}
