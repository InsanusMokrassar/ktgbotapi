package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.photo.inlineQueryResultPhotoType
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

@Serializable
data class InlineQueryResultPhotoImpl(
    @SerialName(idField)
    override val id: String,
    @SerialName(photoUrlField)
    override val url: String,
    @SerialName(thumbUrlField)
    override val thumbUrl: String,
    @SerialName(photoWidthField)
    @Optional
    override val width: Int? = null,
    @SerialName(photoHeightField)
    @Optional
    override val height: Int? = null,
    @SerialName(titleField)
    @Optional
    override val title: String? = null,
    @SerialName(descriptionField)
    @Optional
    override val description: String? = null,
    @SerialName(captionField)
    @Optional
    override val caption: String? = null,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    @Optional
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResultPhoto {
    override val type: String = inlineQueryResultPhotoType
}
