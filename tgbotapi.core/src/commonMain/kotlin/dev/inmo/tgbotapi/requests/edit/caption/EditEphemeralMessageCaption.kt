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
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
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
    chatId: EphemeralChatId,
    caption: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageCaption(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    caption = caption,
    parseMode = parseMode,
    replyMarkup = replyMarkup
)

fun EditEphemeralMessageCaption(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
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

fun EditEphemeralMessageCaption(
    chatId: EphemeralChatId,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageCaption(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    entities = entities,
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
    override val receiverUserId: UserId,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(ephemeralMessageIdField)
    @EncodeDefault
    override val ephemeralMessageId: EphemeralMessageId,
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
