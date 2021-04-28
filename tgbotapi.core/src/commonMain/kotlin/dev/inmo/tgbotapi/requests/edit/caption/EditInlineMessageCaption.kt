package dev.inmo.tgbotapi.requests.edit.caption

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.*

fun EditInlineMessageCaption(
    inlineMessageId: InlineMessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditInlineMessageCaption(
    inlineMessageId,
    text,
    parseMode,
    null,
    replyMarkup
)

fun EditInlineMessageCaption(
    inlineMessageId: InlineMessageIdentifier,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditInlineMessageCaption(
    inlineMessageId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    replyMarkup
)

@Serializable
data class EditInlineMessageCaption internal constructor(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier,
    @SerialName(captionField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditTextChatMessage, EditReplyMessage {
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text)
    }

    override fun method(): String = editMessageCaptionMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
