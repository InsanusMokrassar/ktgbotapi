package dev.inmo.tgbotapi.types.venue

import dev.inmo.tgbotapi.abstracts.CommonVenueData
import dev.inmo.tgbotapi.abstracts.Locationed
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.location.StaticLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    @SerialName(locationField)
    val location: StaticLocation,
    @SerialName(titleField)
    override val title: String,
    @SerialName(addressField)
    override val address: String,
    @SerialName(foursquareIdField)
    override val foursquareId: FoursquareId? = null,
    @SerialName(foursquareTypeField)
    override val foursquareType: FoursquareType? = null,
    @SerialName(googlePlaceIdField)
    override val googlePlaceId: GooglePlaceId? = null,
    @SerialName(googlePlaceTypeField)
    override val googlePlaceType: GooglePlaceType? = null
) : CommonVenueData, Locationed by location
