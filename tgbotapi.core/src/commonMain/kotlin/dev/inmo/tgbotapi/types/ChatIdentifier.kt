package dev.inmo.tgbotapi.types

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlin.jvm.JvmInline

const val internalTgAppLinksBeginning = "tg://"
const val internalLinkBeginning = "https://t.me"
const val internalUserLinkBeginning = "${internalTgAppLinksBeginning}user?id="

@Serializable(ChatIdentifierSerializer::class)
@ClassCastsIncluded
sealed interface ChatIdentifier

/**
 * Also used as User Identifier
 */
@Serializable(ChatIdentifierSerializer::class)
sealed interface IdChatIdentifier : ChatIdentifier {
    abstract val chatId: Identifier
    val threadId: MessageThreadId?
        get() = null

    companion object {
        operator fun invoke(chatId: Identifier) = ChatId(chatId)
        operator fun invoke(chatId: Identifier, threadId: MessageThreadId?) = threadId ?.let {
            ChatIdWithThreadId(chatId, threadId)
        } ?: ChatId(chatId)
    }
}

@Serializable(ChatIdentifierSerializer::class)
@JvmInline
value class ChatId(override val chatId: Identifier) : IdChatIdentifier

@Serializable(ChatIdentifierSerializer::class)
@JvmInline
value class ChatIdWithThreadId(val chatIdWithThreadId: Pair<Identifier, MessageThreadId>) : IdChatIdentifier {
    override val chatId: Identifier
        get() = chatIdWithThreadId.first
    override val threadId: MessageThreadId
        get() = chatIdWithThreadId.second

    constructor(chatId: Identifier, threadId: MessageThreadId): this(chatId to threadId)
}

val ChatIdentifier.threadId: MessageThreadId?
    get() = (this as? IdChatIdentifier) ?.threadId

fun IdChatIdentifier.toChatId() = when (this) {
    is ChatId -> this
    is ChatIdWithThreadId -> ChatId(chatId)
}

fun IdChatIdentifier.toChatWithThreadId(threadId: MessageThreadId) = IdChatIdentifier(chatId, threadId)

/**
 * https://core.telegram.org/bots/api#formatting-options
 */
@Warning("This API have restrictions in Telegram System")
val Identifier.userLink: String
    get() = "$internalUserLinkBeginning$this"
/**
 * https://core.telegram.org/bots/api#formatting-options
 */
@Warning("This API have restrictions in Telegram System")
val UserId.userLink: String
    get() = chatId.userLink
val User.userLink: String
    get() = id.userLink

typealias UserId = ChatId

fun Identifier.toChatId(): ChatId = ChatId(this)
fun Int.toChatId(): IdChatIdentifier = toLong().toChatId()
fun Byte.toChatId(): IdChatIdentifier = toLong().toChatId()

@Serializable(ChatIdentifierSerializer::class)
@JvmInline
value class Username(
    val full: String
) : ChatIdentifier {
    val username: String
        get() = full
    val withoutAt
        get() = full.dropWhile { it == '@' }

    init {
        if (!full.startsWith("@")) {
            throw IllegalArgumentException("Username must starts with `@`")
        }
    }

    override fun toString(): String {
        return full
    }
}

fun String.toUsername(): Username = Username(this)

@RiskFeature
object ChatIdentifierSerializer : KSerializer<ChatIdentifier> {
    private val internalSerializer = JsonPrimitive.serializer()
    override val descriptor: SerialDescriptor = internalSerializer.descriptor
    override fun deserialize(decoder: Decoder): ChatIdentifier {
        val id = internalSerializer.deserialize(decoder)

        return id.longOrNull ?.let {
            ChatId(it)
        } ?: id.content.let {
            if (!it.startsWith("@")) {
                Username("@$it")
            } else {
                Username(it)
            }
        }
    }

    override fun serialize(encoder: Encoder, value: ChatIdentifier) {
        when (value) {
            is IdChatIdentifier -> encoder.encodeLong(value.chatId)
            is Username -> encoder.encodeString(value.full)
        }
    }
}

@RiskFeature
object FullChatIdentifierSerializer : KSerializer<ChatIdentifier> {
    private val internalSerializer = JsonPrimitive.serializer()
    override val descriptor: SerialDescriptor = internalSerializer.descriptor
    override fun deserialize(decoder: Decoder): ChatIdentifier {
        val id = internalSerializer.deserialize(decoder)

        return id.longOrNull ?.let {
            ChatId(it)
        } ?:let {
            val splitted = id.content.split("/")
            if (splitted.size == 2) {
                val (chatId, threadId) = splitted
                ChatIdWithThreadId(
                    chatId.toLongOrNull() ?: return@let null,
                    threadId.toLongOrNull() ?: return@let null
                )
            } else {
                null
            }
        } ?: id.content.let {
            if (!it.startsWith("@")) {
                Username("@$it")
            } else {
                Username(it)
            }
        }
    }

    override fun serialize(encoder: Encoder, value: ChatIdentifier) {
        when (value) {
            is ChatId -> encoder.encodeLong(value.chatId)
            is ChatIdWithThreadId -> encoder.encodeString("${value.chatId}/${value.threadId}")
            is Username -> encoder.encodeString(value.full)
        }
    }
}
