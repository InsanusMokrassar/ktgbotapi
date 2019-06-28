package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InputMessageContent

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.DisableWebPagePreview
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.disableWebPagePreviewField
import com.github.insanusmokrassar.TelegramBotAPI.types.messageTextField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InputTextMessageContent(
    @SerialName(messageTextField)
    override val caption: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(disableWebPagePreviewField)
    override val disableWebPagePreview: Boolean? = null
) : CaptionedOutput, DisableWebPagePreview, InputMessageContent