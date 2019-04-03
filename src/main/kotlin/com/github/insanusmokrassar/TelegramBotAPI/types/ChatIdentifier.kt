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

fun String.asUsername(): Username = Username(this)

@Serializer(ChatIdentifier::class)
internal class ChatIdentifierSerializer: KSerializer<ChatIdentifier> {
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
            is ChatId -> encoder.encodeString(obj.chatId.toString())
            is Username -> encoder.encodeString(obj.username)
        }
    }
}