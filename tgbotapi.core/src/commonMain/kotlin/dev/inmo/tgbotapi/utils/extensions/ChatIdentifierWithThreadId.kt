package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.MessageThreadId
import kotlinx.serialization.Serializable
import kotlin.Pair
import dev.inmo.tgbotapi.types.message.abstract.Message

sealed interface ChatIdWithThreadId {
    val chatId: ChatId
    val threadId: MessageThreadId?

    // Light weight due to absence of any conversations
    value class ByMessage(
        val sourceMessage: Message
    ) : ChatIdWithThreadId {
        override val chatId: ChatId
            get() = sourceMessage.chat.id
        override val threadId: MessageThreadId?
            get() = sourceMessage.threadIdOrNull
    }
    @Serializable
    value class ByPair(
        val pair: Pair<ChatId, MessageThreadId?>
    ) : ChatIdWithThreadId {
        override val chatId: ChatId
            get() = pair.first
        override val threadId: MessageThreadId?
            get() = pair.second
    }

    companion {
        inline operator fun invoke(message: Message) = ByMessage(message)
        inline fun serializable(message: Message) = ByPair(message.chatId.id to message.threadIdOrNull)
        inline fun serializable(pair: Pair<ChatId, MessageThreadId?>) = ByPair(pair)
    }
}

val Message.chatIdWithThreadId
    get() = ChatIdWithThreadId(this)
val Message.serializableChatIdWithThreadId
    get() = ChatIdWithThreadId.serializable(this)
