package dev.inmo.tgbotapi.types

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.longOrNull

@Serializable(ChatIdentifierSerializer::class)
sealed class ChatIdentifier

/**
 * Also used as User Identifier
 */
@Serializable(ChatIdentifierSerializer::class)
data class ChatId(
    val chatId: Identifier
) : ChatIdentifier()

/**
 * https://core.telegram.org/bots/api#formatting-options
 */
@Warning("This API have restrictions in Telegram System")
val Identifier.link: String
    get() = "tg://user?id=$this"
/**
 * https://core.telegram.org/bots/api#formatting-options
 */
@Warning("This API have restrictions in Telegram System")
val UserId.link: String
    get() = chatId.link
val User.link: String
    get() = id.link

typealias UserId = ChatId

fun Identifier.toChatId(): ChatId = ChatId(this)
fun Int.toChatId(): ChatId = toLong().toChatId()
fun Byte.toChatId(): ChatId = toLong().toChatId()

@Serializable(ChatIdentifierSerializer::class)
data class Username(
    val username: String
) : ChatIdentifier() {
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
            is ChatId -> encoder.encodeLong(value.chatId)
            is Username -> encoder.encodeString(value.username)
        }
    }
}
