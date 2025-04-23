package dev.inmo.tgbotapi.requests.edit.text

import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.WithCustomizableCaptionRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

fun EditInlineMessageText(
    inlineMessageId: InlineMessageId,
    text: String,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
) = EditInlineMessageText(
    inlineMessageId = inlineMessageId,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    showCaptionAboveMedia = showCaptionAboveMedia,
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup,
)

fun EditInlineMessageText(
    inlineMessageId: InlineMessageId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
) = EditInlineMessageText(
    inlineMessageId = inlineMessageId,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    showCaptionAboveMedia = showCaptionAboveMedia,
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup,
)

@Serializable
data class EditInlineMessageText internal constructor(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageId,
    @SerialName(textField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(entitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(showCaptionAboveMediaField)
    override val showCaptionAboveMedia: Boolean = false,
    @SerialName(linkPreviewOptionsField)
    override val linkPreviewOptions: LinkPreviewOptions? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
) : EditInlineMessage, WithCustomizableCaptionRequest<Boolean>, EditTextChatMessage, EditReplyMessage, EditLinkPreviewOptionsContainer {
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    override fun method(): String = editMessageTextMethod

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
