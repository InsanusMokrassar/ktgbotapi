package dev.inmo.tgbotapi.requests.edit.caption

import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

fun EditInlineMessageCaption(
    inlineMessageId: InlineMessageId,
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
    inlineMessageId: InlineMessageId,
    entities: TextSourcesList,
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
    override val inlineMessageId: InlineMessageId,
    @SerialName(captionField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditInlineMessage, EditTextChatMessage, EditReplyMessage {
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    override fun method(): String = editMessageCaptionMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
