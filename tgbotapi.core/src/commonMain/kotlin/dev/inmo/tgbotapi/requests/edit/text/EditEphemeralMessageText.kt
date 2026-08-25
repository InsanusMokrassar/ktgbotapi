package dev.inmo.tgbotapi.requests.edit.text

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.MultipartRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.rich.InputRichMessage
import dev.inmo.tgbotapi.types.rich.multipartFiles
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

const val editEphemeralMessageTextMethod = "editEphemeralMessageText"

fun EditEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageText(
    chatId = chatId,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

fun EditEphemeralMessageRichText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    richMessage: InputRichMessage,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageText(
    chatId = chatId,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    text = null,
    parseMode = null,
    rawEntities = null,
    linkPreviewOptions = null,
    replyMarkup = replyMarkup,
    richMessage = richMessage
)

fun EditEphemeralMessageRichText(
    chatId: EphemeralChatId,
    richMessage: InputRichMessage,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageRichText(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    richMessage = richMessage,
    replyMarkup = replyMarkup
)

fun EditEphemeralMessageText(
    chatId: EphemeralChatId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageText(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    text = text,
    parseMode = parseMode,
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

fun EditEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageText(
    chatId = chatId,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

fun EditEphemeralMessageText(
    chatId: EphemeralChatId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = EditEphemeralMessageText(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    entities = entities,
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

@ConsistentCopyVisibility
@Serializable
data class EditEphemeralMessageText internal constructor(
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
    @SerialName(textField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(entitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(linkPreviewOptionsField)
    override val linkPreviewOptions: LinkPreviewOptions? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(richMessageField)
    val richMessage: InputRichMessage? = null
) : EditEphemeralMessage,
    EditTextChatMessage,
    EditReplyMessage,
    EditLinkPreviewOptionsContainer,
    MultipartRequest.Common<Unit> {
    override val textSources: TextSourcesList? by lazy {
        text ?.let { rawEntities ?.asTextSources(it) }
    }

    init {
        require((text == null) != (richMessage == null)) { "Exactly one of text and richMessage must be specified" }
        text ?.let {
            if (it.length !in textLength) {
                throwRangeError("Text length", textLength, it.length)
            }
        }
    }

    override fun method(): String = editEphemeralMessageTextMethod
    override val data: SimpleRequest<Unit>
        get() = this
    override val mediaMap: Map<String, MultipartFile>
        get() = richMessage ?.multipartFiles ?: emptyMap()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
