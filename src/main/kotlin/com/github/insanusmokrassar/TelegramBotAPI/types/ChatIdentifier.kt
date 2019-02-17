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

typealias UserId = ChatId

fun Identifier.toChatId(): ChatId = ChatId(this)

@Serializable(ChatIdentifierSerializer::class)
data class Username(
    private val baseUsername: String
) : ChatIdentifier() {
    @Transient
    val username: String = if (!baseUsername.startsWith("@")) {
        "@$baseUsername"
    } else {
        baseUsername
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other) || other ?.let {
            super.equals("@$it")
        } ?: false
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