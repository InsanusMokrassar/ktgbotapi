package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.abstracts.CommonVenueData
import dev.inmo.tgbotapi.abstracts.Locationed
import dev.inmo.tgbotapi.types.FoursquareId
import dev.inmo.tgbotapi.types.FoursquareType
import dev.inmo.tgbotapi.types.GooglePlaceId
import dev.inmo.tgbotapi.types.GooglePlaceType
import dev.inmo.tgbotapi.types.addressField
import dev.inmo.tgbotapi.types.foursquareIdField
import dev.inmo.tgbotapi.types.foursquareTypeField
import dev.inmo.tgbotapi.types.googlePlaceIdField
import dev.inmo.tgbotapi.types.googlePlaceTypeField
import dev.inmo.tgbotapi.types.latitudeField
import dev.inmo.tgbotapi.types.longitudeField
import dev.inmo.tgbotapi.types.titleField
import dev.inmo.tgbotapi.types.typeField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TelegramMediaVenue(
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
) : CommonVenueData, Locationed, InputPollMedia, InputPollOptionMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "venue"
    }
}
