package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

fun SendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    text: String,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId
) = SendMessageDraft(
    chatId = chatId,
    draftId = draftId,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    threadId = threadId
)

fun SendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId
) = SendMessageDraft(
    chatId = chatId,
    draftId = draftId,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    threadId = threadId
)

@ConsistentCopyVisibility
@Serializable
data class SendMessageDraft internal constructor(
    @SerialName(chatIdField)
    override val chatId: IdChatIdentifier,
    @SerialName(draftIdField)
    val draftId: DraftId,
    @SerialName(textField)
    override val text: String,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(entitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(messageThreadIdField)
    @EncodeDefault
    override val threadId: MessageThreadId? = chatId.threadId
) : SendChatMessageRequest<Boolean>,
    TextedOutput,
    OptionallyMessageThreadRequest
{
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    init {
        if (text.length !in textLength) {
            throwRangeError("Text length", textLength, text.length)
        }
    }

    override fun method(): String = "sendMessageDraft"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
