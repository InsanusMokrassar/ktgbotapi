package dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent

import dev.inmo.tgbotapi.CommonAbstracts.CommonVenueData
import dev.inmo.tgbotapi.CommonAbstracts.Locationed
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InputVenueMessageContent(
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
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
) : Locationed, CommonVenueData, InputMessageContent
