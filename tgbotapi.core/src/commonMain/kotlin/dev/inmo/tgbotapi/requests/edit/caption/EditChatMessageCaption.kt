package dev.inmo.tgbotapi.requests.edit.caption

import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.requests.edit.media.MediaContentMessageResultDeserializer
import dev.inmo.tgbotapi.requests.send.abstracts.WithCustomizableCaptionRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MediaContent
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

const val editMessageCaptionMethod = "editMessageCaption"

fun EditChatMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
) = EditChatMessageCaption(
    chatId = chatId,
    messageId = messageId,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    businessConnectionId = businessConnectionId,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
)

fun EditChatMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageId,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null,
) = EditChatMessageCaption(
    chatId = chatId,
    messageId = messageId,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    businessConnectionId = businessConnectionId,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup,
)

@Serializable
data class EditChatMessageCaption internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @SerialName(captionField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(showCaptionAboveMediaField)
    override val showCaptionAboveMedia: Boolean = false,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
) : EditChatMessage<MediaContent>, WithCustomizableCaptionRequest<ContentMessage<MediaContent>>, EditTextChatMessage, EditReplyMessage {
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    override fun method(): String = editMessageCaptionMethod

    override val resultDeserializer: DeserializationStrategy<ContentMessage<MediaContent>>
        get() = MediaContentMessageResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
