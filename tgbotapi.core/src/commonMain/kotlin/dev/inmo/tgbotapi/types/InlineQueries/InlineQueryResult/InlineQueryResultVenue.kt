package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.abstracts.CommonVenueData
import dev.inmo.tgbotapi.abstracts.Locationed
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultVenue(
    @SerialName(idField)
    override val id: InlineQueryId,
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
    override val googlePlaceType: GooglePlaceType? = null,
    @SerialName(thumbnailUrlField)
    override val thumbnailUrl: String? = null,
    @SerialName(thumbnailWidthField)
    override val thumbnailWidth: Int? = null,
    @SerialName(thumbnailHeightField)
    override val thumbnailHeight: Int? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult,
    Locationed,
    CommonVenueData,
    TitledInlineQueryResult,
    WithInputMessageContentInlineQueryResult,
    ThumbedInlineQueryResult,
    ThumbSizedInlineQueryResult {
    override val type: String = "venue"
}
