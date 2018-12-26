package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*

@Serializable(ChatIdentifierSerializer::class)
sealed class ChatIdentifier

/**
 * Also used as User Identifier
 */
@Serializable(ChatIdentifierSerializer::class)
class ChatId(
    val chatId: Identifier
) : ChatIdentifier()

typealias UserId = ChatId

fun Identifier.toChatId(): ChatId = ChatId(this)

@Serializable(ChatIdentifierSerializer::class)
class Username(
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
    override fun deserialize(input: Decoder): ChatIdentifier {
        val id = input.decodeString()
        return id.toLongOrNull() ?.let {
            ChatId(it)
        } ?: Username(id)
    }

    override fun serialize(output: Encoder, obj: ChatIdentifier) {
        when (obj) {
            is ChatId -> output.encodeString(obj.chatId.toString())
            is Username -> output.encodeString(obj.username)
        }
    }
}