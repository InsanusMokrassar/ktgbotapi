package dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.CommonAbstracts.types.DisableWebPagePreview
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.disableWebPagePreviewField
import dev.inmo.tgbotapi.types.messageTextField
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