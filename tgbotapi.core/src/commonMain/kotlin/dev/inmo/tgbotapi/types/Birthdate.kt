package dev.inmo.tgbotapi.types

import korlibs.time.Date
import korlibs.time.DateTime
import korlibs.time.Year
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Birthdate(
    @SerialName(dayField)
    val day: Int,
    @SerialName(monthField)
    val month: Int,
    @SerialName(yearField)
    val year: Int? = null
) {
    /**
     * Represents this birthday as korlibs [Date]. Will use this year in case if [year] has not been retrieved
     */
    val date: Date by lazy {
        Date(year ?: DateTime.now().year.year, month, day)
    }
}
