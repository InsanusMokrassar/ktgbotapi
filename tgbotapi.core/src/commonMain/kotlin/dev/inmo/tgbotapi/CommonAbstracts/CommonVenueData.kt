package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.FoursquareId
import dev.inmo.tgbotapi.types.FoursquareType
import dev.inmo.tgbotapi.types.GooglePlaceId
import dev.inmo.tgbotapi.types.GooglePlaceType

interface CommonVenueData : Titled {
    override val title: String
    val address: String
    val foursquareId: FoursquareId?
    val foursquareType: FoursquareType? // TODO:: Rewrite with enum or interface
    val googlePlaceId: GooglePlaceId?
    val googlePlaceType: GooglePlaceType?
}
