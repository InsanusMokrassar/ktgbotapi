package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.abstracts.WithPreviewChat
import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.extensions.utils.usernameChatOrNull
import dev.inmo.tgbotapi.extensions.utils.whenUsernameChat
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.threadId
import dev.inmo.tgbotapi.utils.extensions.threadIdOrNull

/**
 * @return true in case if [this] message is placed in the chat with id == [chatId]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun WithPreviewChat.sameChat(chatId: ChatIdentifier) = chat.id == chatId || (
    chatId is Username && chat.whenUsernameChat {
        it.username == chatId
    } ?: false
    )

/**
 * @return true in case if [this] message is placed in the [chat]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun WithPreviewChat.sameChat(chat: Chat) = sameChat(chat.id) || chat.usernameChatOrNull() ?.username ?.let { sameChat(it) } ?: false

/**
 * @return true in case if [this] message is placed in the same chat that [other]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun WithPreviewChat.sameChat(other: Message) = sameChat(other.chat)

/**
 * @return true in case if [this] message is from the same chat (with id == [chatId]) and [this] [Message.messageId]
 * equal [messageId] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameMessage(
    chatId: ChatIdentifier,
    messageId: MessageId,
) = sameChat(chatId) && this.messageId == messageId

/**
 * @return true in case if [this] message is from the same [chat] and [this] [Message.messageId] equal [messageId]
 * identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameMessage(
    chat: Chat,
    messageId: MessageId,
) = sameChat(chat) && this.messageId == messageId

/**
 * @return true in case if [this] message is the same as [other]. The same here means that these messages from one chat
 * and have equal [Message.messageId] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameMessage(other: Message) = sameMessage(other.chat, other.messageId)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is in the chat [chatId] and topic [threadId]. The same here means that these
 * messages from one chat and have equal [Message.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameTopic(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
) = sameChat(chatId) && threadIdOrNull == threadId

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is in the chat [chatId] and topic [threadId]. The same here means that these
 * messages from one chat and have equal [Message.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameThread(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
) = sameTopic(chatId, threadId)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is from the [chat] and topic [threadId]. The same here means that these
 * messages from one chat and have equal [Message.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameTopic(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
) = sameTopic(chat.id, threadId)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is from the [chat] and topic [threadId]. The same here means that these
 * messages from one chat and have equal [Message.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameThread(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
) = sameThread(chat.id, threadId)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is from the same chat and topic as [other]. The same here means that these
 * messages from one chat and have equal [Message.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameTopic(other: Message) = sameTopic(other.chat, other.threadIdOrNull)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is in the same topic as the [other]. The same here means that these messages
 * from one chat and have equal [Message.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameThread(other: Message) = sameTopic(other)

/**
 * @return true in case if [this] message is from the same business connection (with businessConnectionId == [id])
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameBusinessConnection(id: BusinessConnectionId) = businessConnectionId == id

/**
 * @return true in case if [this] message is from the same business connection (with businessConnectionId == [other.businessConnectionId])
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameBusinessConnection(other: WithBusinessConnectionId) = sameBusinessConnection(other.businessConnectionId)
