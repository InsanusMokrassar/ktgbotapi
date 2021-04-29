package dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.CommonAbstracts.types.DisableWebPagePreview
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the [InputMessageContent] of a text message to be sent as the result of an inline query.
 */
fun InputTextMessageContent(
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null
) = InputTextMessageContent(text, parseMode, null, disableWebPagePreview)

/**
 * Represents the [InputMessageContent] of a text message to be sent as the result of an inline query.
 */
fun InputTextMessageContent(
    entities: List<TextSource>,
    disableWebPagePreview: Boolean? = null
) = InputTextMessageContent(entities.makeString(), null, entities.toRawMessageEntities(), disableWebPagePreview)

@Serializable
data class InputTextMessageContent internal constructor(
    @SerialName(messageTextField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(entitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(disableWebPagePreviewField)
    override val disableWebPagePreview: Boolean? = null
) : TextedOutput, DisableWebPagePreview, InputMessageContent {
    override val textSources: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text)
    }
}