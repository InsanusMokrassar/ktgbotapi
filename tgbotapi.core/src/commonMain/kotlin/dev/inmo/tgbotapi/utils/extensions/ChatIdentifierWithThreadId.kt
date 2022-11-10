package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageThreadId
import kotlinx.serialization.Serializable
import kotlin.Pair
import dev.inmo.tgbotapi.types.message.abstract.Message

sealed interface ChatIdWithThreadId {
    val chatId: ChatIdentifier
    val threadId: MessageThreadId?

    // Light weight due to absence of any conversations
    value class ByMessage(
        val sourceMessage: Message
    ) : ChatIdWithThreadId {
        override val chatId: ChatIdentifier
            get() = sourceMessage.chat.id
        override val threadId: MessageThreadId?
            get() = sourceMessage.threadIdOrNull
    }
    @Serializable
    value class ByPair(
        val pair: Pair<ChatIdentifier, MessageThreadId?>
    ) : ChatIdWithThreadId {
        override val chatId: ChatIdentifier
            get() = pair.first
        override val threadId: MessageThreadId?
            get() = pair.second
    }

    companion {
        inline operator fun invoke(message: Message) = ByMessage(message)
        inline fun serializable(message: Message) = ByPair(message.chatId.id to message.threadIdOrNull)
        inline fun serializable(pair: Pair<ChatIdentifier, MessageThreadId?>) = ByPair(pair)
    }
}

val Message.chatIdWithThreadId
    get() = ChatIdWithThreadId(this)
val Message.serializableChatIdWithThreadId
    get() = ChatIdWithThreadId.serializable(this)
