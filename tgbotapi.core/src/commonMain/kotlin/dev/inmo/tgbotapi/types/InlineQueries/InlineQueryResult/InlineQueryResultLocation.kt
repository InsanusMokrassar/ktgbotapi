package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultLocation(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(horizontalAccuracyField)
    override val horizontalAccuracy: Meters? = null,
    @SerialName(titleField)
    override val title: String,
    @SerialName(livePeriodField)
    override val livePeriod: Seconds? = null,
    @SerialName(headingField)
    override val heading: Degrees? = null,
    @SerialName(proximityAlertRadiusField)
    override val proximityAlertRadius: Meters? = null,
    @SerialName(thumbnailUrlField)
    override val thumbnailUrl: String? = null,
    @SerialName(thumbnailWidthField)
    override val thumbnailWidth: Int? = null,
    @SerialName(thumbnailHeightField)
    override val thumbnailHeight: Int? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult,
    Locationed,
    HorizontallyAccured,
    Livable,
    ProximityAlertable,
    Headed,
    TitledInlineQueryResult,
    WithInputMessageContentInlineQueryResult,
    ThumbedInlineQueryResult,
    ThumbSizedInlineQueryResult
{
    override val type: String = "location"
}
