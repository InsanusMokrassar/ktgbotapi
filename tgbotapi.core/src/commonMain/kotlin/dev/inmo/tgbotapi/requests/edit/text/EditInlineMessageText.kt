package dev.inmo.tgbotapi.requests.edit.text

import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

fun EditInlineMessageText(
    inlineMessageId: InlineMessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditInlineMessageText(
    inlineMessageId,
    text,
    parseMode,
    null,
    disableWebPagePreview,
    replyMarkup
)

fun EditInlineMessageText(
    inlineMessageId: InlineMessageIdentifier,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditInlineMessageText(
    inlineMessageId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    disableWebPagePreview,
    replyMarkup
)

@Serializable
data class EditInlineMessageText internal constructor(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier,
    @SerialName(textField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(entitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(disableWebPagePreviewField)
    override val disableWebPagePreview: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditTextChatMessage, EditReplyMessage, EditDisableWebPagePreviewMessage {
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    override fun method(): String = editMessageTextMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
