package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocument
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.document.inlineQueryResultDocumentType
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.mimeTypeField
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultDocumentImpl(
    @SerialName(idField)
    override val id: String,
    @SerialName(documentUrlField)
    override val url: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(thumbUrlField)
    @Optional
    override val thumbUrl: String? = null,
    @SerialName(thumbWidthField)
    @Optional
    override val thumbWidth: Int? = null,
    @SerialName(thumbHeightField)
    @Optional
    override val thumbHeight: Int? = null,
    @SerialName(mimeTypeField)
    override val mimeType: String? = null,
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
) : InlineQueryResultDocument {
    override val type: String = inlineQueryResultDocumentType
}
