package dev.inmo.tgbotapi.types.venue

import dev.inmo.tgbotapi.CommonAbstracts.CommonVenueData
import dev.inmo.tgbotapi.CommonAbstracts.Locationed
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    @SerialName(locationField)
    val location: Location,
    @SerialName(titleField)
    override val title: String,
    @SerialName(addressField)
    override val address: String,
    @SerialName(foursquareIdField)
    override val foursquareId: FoursquareId? = null,
    @SerialName(foursquareTypeField)
    override val foursquareType: FoursquareType? = null
) : CommonVenueData, Locationed by location
