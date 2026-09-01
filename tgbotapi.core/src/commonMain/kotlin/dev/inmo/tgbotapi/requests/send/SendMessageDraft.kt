package dev.inmo.tgbotapi.requests.send

import dev.inmo.kslog.common.w
import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*

/**
 * Creates a plain-text draft for a direct messages topic.
 *
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
fun SendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    text: String,
    parseMode: ParseMode? = null,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
) = SendMessageDraft(
    chatId = chatId,
    draftId = draftId,
    text = text,
    parseMode = parseMode,
    rawEntities = null,
    directMessageThreadId = directMessageThreadId,
    canStop = canStop,
    keepOnStop = keepOnStop
)

/**
 * Creates an entity-formatted draft for a direct messages topic.
 *
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
fun SendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    entities: TextSourcesList,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
) = SendMessageDraft(
    chatId = chatId,
    draftId = draftId,
    text = entities.makeString(),
    parseMode = null,
    rawEntities = entities.toRawMessageEntities(),
    directMessageThreadId = directMessageThreadId,
    canStop = canStop,
    keepOnStop = keepOnStop
)

/**
 * Streams a partial text message draft to a direct messages topic.
 *
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
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
    @SerialName(directMessagesTopicIdField)
    @EncodeDefault
    override val directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    @SerialName(canStopField)
    val canStop: Boolean? = null,
    @SerialName(keepOnStopField)
    val keepOnStop: Boolean? = null
) : SendChatMessageRequest<Unit>,
    TextedOutput,
    OptionallyDirectMessageThreadRequest
{
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    init {
        if (text.length !in draftMessageTextLength) {
            DefaultKTgBotAPIKSLog.w("For sendMessageDraft it is required to have length of text in range $draftMessageTextLength, but actual length is ${text.length}")
        }
    }

    override fun method(): String = "sendMessageDraft"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
