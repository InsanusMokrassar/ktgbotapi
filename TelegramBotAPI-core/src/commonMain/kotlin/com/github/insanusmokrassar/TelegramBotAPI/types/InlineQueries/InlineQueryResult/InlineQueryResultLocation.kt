package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Livable
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Locationed
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
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
