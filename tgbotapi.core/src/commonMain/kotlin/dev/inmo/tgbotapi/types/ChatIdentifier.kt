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

const val internalLinkBeginning = "https://t.me"

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

/**
 * https://core.telegram.org/bots/api#formatting-options
 */
@Warning("This API have restrictions in Telegram System")
val Identifier.userLink: String
    get() = "tg://user?id=$this"
/**
 * https://core.telegram.org/bots/api#formatting-options
 */
@Warning("This API have restrictions in Telegram System")
val UserId.userLink: String
    get() = chatId.userLink
val User.link: String
    get() = id.userLink

typealias UserId = IdChatIdentifier

fun Identifier.toChatId(): IdChatIdentifier = ChatId(this)
fun Int.toChatId(): IdChatIdentifier = toLong().toChatId()
fun Byte.toChatId(): IdChatIdentifier = toLong().toChatId()

@Serializable(ChatIdentifierSerializer::class)
data class Username(
    val username: String
) : ChatIdentifier {
    val usernameWithoutAt
        get() = username.dropWhile { it == '@' }

    init {
        if (!username.startsWith("@")) {
            throw IllegalArgumentException("Username must starts with `@`")
        }
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
            is Username -> encoder.encodeString(value.username)
        }
    }
}
