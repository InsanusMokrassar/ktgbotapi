package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.CommonAbstracts.Livable
import dev.inmo.tgbotapi.CommonAbstracts.Locationed
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
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
    @SerialName(titleField)
    override val title: String,
    @SerialName(livePeriodField)
    override val livePeriod: Int? = null,
    @SerialName(thumbUrlField)
    override val thumbUrl: String? = null,
    @SerialName(thumbWidthField)
    override val thumbWidth: Int? = null,
    @SerialName(thumbHeightField)
    override val thumbHeight: Int? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult,
    Locationed,
    Livable,
    TitledInlineQueryResult,
    WithInputMessageContentInlineQueryResult,
    ThumbedInlineQueryResult,
    ThumbSizedInlineQueryResult
{
    override val type: String = "location"
}
