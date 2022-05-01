package dev.inmo.tgbotapi.requests.edit.text

import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.requests.send.TextContentMessageResultDeserializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

const val editMessageTextMethod = "editMessageText"

fun EditChatMessageText(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditChatMessageText(
    chatId,
    messageId,
    text,
    parseMode,
    null,
    disableWebPagePreview,
    replyMarkup
)

fun EditChatMessageText(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditChatMessageText(
    chatId,
    messageId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    disableWebPagePreview,
    replyMarkup
)

@Serializable
data class EditChatMessageText internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
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
) : EditChatMessage<TextContent>, EditTextChatMessage, EditReplyMessage, EditDisableWebPagePreviewMessage {
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    override fun method(): String = editMessageTextMethod
    override val resultDeserializer: DeserializationStrategy<ContentMessage<TextContent>>
        get() = TextContentMessageResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
