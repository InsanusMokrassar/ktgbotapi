package dev.inmo.tgbotapi.extensions.utils.extensions.venue

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.venue.Venue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

val Venue.foursquare: Foursquare?
    get() = foursquareId?.let {
        Foursquare(it, foursquareType)
    }

fun Venue(
    location: StaticLocation,
    title: String,
    address: String,
    foursquare: Foursquare,
) = Venue(location, title, address, foursquareId = foursquare.id, foursquareType = foursquare.type)

@Serializable
data class Foursquare(
    @SerialName(foursquareIdField)
    val id: FoursquareId,
    @SerialName(foursquareTypeField)
    val type: FoursquareType? = null,
)
