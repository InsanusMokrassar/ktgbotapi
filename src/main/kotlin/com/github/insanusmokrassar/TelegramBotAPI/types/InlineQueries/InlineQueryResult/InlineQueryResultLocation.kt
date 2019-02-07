package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Livable
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Locationed
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.mimeTypeField
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName

data class InlineQueryResultLocation(
    @SerialName(idField)
    override val id: String,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(livePeriodField)
    @Optional
    override val livePeriod: Int? = null,
    @SerialName(thumbUrlField)
    @Optional
    override val thumbUrl: String? = null,
    @SerialName(thumbWidthField)
    @Optional
    override val thumbWidth: Int? = null,
    @SerialName(thumbHeightField)
    @Optional
    override val thumbHeight: Int? = null,
    @SerialName(titleField)
    @Optional
    override val title: String? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    @Optional
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
