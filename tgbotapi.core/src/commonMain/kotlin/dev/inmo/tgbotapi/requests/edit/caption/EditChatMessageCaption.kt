package dev.inmo.tgbotapi.requests.edit.caption

import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.requests.edit.media.MediaContentMessageResultDeserializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent
import kotlinx.serialization.*

const val editMessageCaptionMethod = "editMessageCaption"

fun EditChatMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditChatMessageCaption(
    chatId,
    messageId,
    text,
    parseMode,
    null,
    replyMarkup
)

fun EditChatMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditChatMessageCaption(
    chatId,
    messageId,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    replyMarkup
)

@Serializable
data class EditChatMessageCaption internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(captionField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditChatMessage<MediaContent>, EditTextChatMessage, EditReplyMessage {
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    override fun method(): String = editMessageCaptionMethod
    override val resultDeserializer: DeserializationStrategy<ContentMessage<MediaContent>>
        get() = MediaContentMessageResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
