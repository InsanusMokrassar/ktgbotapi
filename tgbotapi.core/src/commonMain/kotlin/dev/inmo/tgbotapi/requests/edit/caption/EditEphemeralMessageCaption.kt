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

const val editEphemeralMessageCaptionMethod = "editEphemeralMessageCaption"

fun EditEphemeralMessageCaption(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    caption: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageCaption(
    chatId = chatId,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    text = caption,
    parseMode = parseMode,
    rawEntities = null,
    replyMarkup = replyMarkup
)

fun EditEphemeralMessageCaption(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageCaption(
    chatId = chatId,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    replyMarkup = replyMarkup
)

@ConsistentCopyVisibility
@Serializable
data class EditEphemeralMessageCaption internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(receiverUserIdField)
    @EncodeDefault
    override val receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(ephemeralMessageIdField)
    @EncodeDefault
    override val ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditEphemeralMessage, EditTextChatMessage, EditReplyMessage {
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    override fun method(): String = editEphemeralMessageCaptionMethod
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
