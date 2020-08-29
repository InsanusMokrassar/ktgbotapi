package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(ChatIdentifierSerializer::class)
sealed class ChatIdentifier

/**
 * Also used as User Identifier
 */
@Serializable(ChatIdentifierSerializer::class)
data class ChatId(
    val chatId: Identifier
) : ChatIdentifier()


val ChatId.link: String
    get() = "tg://user?id=$chatId"

typealias UserId = ChatId

fun Identifier.toChatId(): ChatId = ChatId(this)
fun Int.toChatId(): ChatId = toLong().toChatId()
fun Byte.toChatId(): ChatId = toLong().toChatId()

@Serializable(ChatIdentifierSerializer::class)
data class Username(
    val username: String
) : ChatIdentifier() {
    init {
        if (!username.startsWith("@")) {
            throw IllegalArgumentException("Username must starts with `@`")
        }
    }
}

fun String.toUsername(): Username = Username(this)

@Serializer(ChatIdentifier::class)
internal object ChatIdentifierSerializer : KSerializer<ChatIdentifier> {
    override fun deserialize(decoder: Decoder): ChatIdentifier {
        val id = JsonPrimitive.serializer().deserialize(decoder)
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