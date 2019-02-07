package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.document.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.mimeTypeField
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultDocumentCachedImpl(
    @SerialName(idField)
    override val id: String,
    @SerialName(documentFileIdField)
    override val fileId: FileId,
    @SerialName(titleField)
    override val title: String,
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
) : InlineQueryResultDocumentCached {
    override val type: String = inlineQueryResultDocumentType
}
