package dev.inmo.tgbotapi.extensions.utils.extensions.venue

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.venue.Venue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

val Venue.googlePlace: GooglePlace?
    get() = googlePlaceId?.let {
        GooglePlace(it, googlePlaceType)
    }

fun Venue(
    location: StaticLocation,
    title: String,
    address: String,
    googlePlace: GooglePlace,
) = Venue(location, title, address, googlePlaceId = googlePlace.id, googlePlaceType = googlePlace.type)

@Serializable
data class GooglePlace(
    @SerialName(googlePlaceIdField)
    val id: GooglePlaceId,
    @SerialName(googlePlaceTypeField)
    val type: GooglePlaceType? = null,
)
