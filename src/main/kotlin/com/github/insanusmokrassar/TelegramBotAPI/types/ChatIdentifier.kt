package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*

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
        val id = decoder.decodeString()
        return id.toLongOrNull() ?.let {
            ChatId(it)
        } ?: if (!id.startsWith("@")) {
            Username("@$id")
        } else {
            Username(id)
        }
    }

    override fun serialize(encoder: Encoder, obj: ChatIdentifier) {
        when (obj) {
            is ChatId -> encoder.encodeLong(obj.chatId)
            is Username -> encoder.encodeString(obj.username)
        }
    }
}