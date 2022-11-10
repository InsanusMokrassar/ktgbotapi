package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.message.abstracts.Message
import kotlinx.serialization.Serializable
import kotlin.Pair
import kotlin.jvm.JvmInline

/**
 * Union to keep both [IdChatIdentifier] and optionally [MessageThreadId] as an identifier of target for messages sending and
 * other information
 */
sealed interface ChatIdWithThreadId {
    val chatId: IdChatIdentifier
    val threadId: MessageThreadId?

    /**
     * Lightweight variant of [ChatIdWithThreadId] due to absence of any conversations
     */
    @JvmInline
    value class ByMessage(
        val sourceMessage: Message
    ) : ChatIdWithThreadId {
        override val chatId: IdChatIdentifier
            get() = sourceMessage.chat.id
        override val threadId: MessageThreadId?
            get() = sourceMessage.threadIdOrNull
    }

    /**
     * [Serializable] variant of [ChatIdWithThreadId] based on [Pair] of target [IdChatIdentifier] and [MessageThreadId]
     *
     * @see invoke
     * @see serializable
     */
    @Serializable
    @JvmInline
    value class ByPair(
        val pair: Pair<IdChatIdentifier, MessageThreadId?>
    ) : ChatIdWithThreadId {
        override val chatId: IdChatIdentifier
            get() = pair.first
        override val threadId: MessageThreadId?
            get() = pair.second
    }

    companion object {
        /**
         * Creates lightweight [ByMessage] variant of [ChatIdWithThreadId]
         */
        inline operator fun invoke(message: Message) = ByMessage(message)

        /**
         * Creates [ByPair] variant of [ChatIdWithThreadId] using incoming [message] [Message.chat] and extension
         * [Message.threadIdOrNull]
         */
        inline fun serializable(message: Message) = ByPair(message.chat.id to message.threadIdOrNull)

        /**
         * Creates [ByPair] variant of [ChatIdWithThreadId] using incoming [pair]
         */
        inline fun serializable(pair: Pair<IdChatIdentifier, MessageThreadId?>) = ByPair(pair)
    }
}
/**
 * Creates [ChatIdWithThreadId.ByMessage] variant of [ChatIdWithThreadId] using [ChatIdWithThreadId.serializable]
 */
val Message.chatIdWithThreadId
    get() = ChatIdWithThreadId(this)

/**
 * Creates [ChatIdWithThreadId.ByPair] variant of [ChatIdWithThreadId] using [ChatIdWithThreadId.serializable]
 */
val Message.serializableChatIdWithThreadId
    get() = ChatIdWithThreadId.serializable(this)
