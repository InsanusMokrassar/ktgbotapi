package dev.inmo.tgbotapi.requests.edit.text

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.requests.edit.media.editMessageMediaMethod
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.MessageEntity.RawMessageEntity
import dev.inmo.tgbotapi.types.MessageEntity.toRawMessageEntities
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
    entities: List<TextSource>,
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
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextParts(text ?: return@lazy null) ?.justTextSources()
    }

    override fun method(): String = editMessageMediaMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
