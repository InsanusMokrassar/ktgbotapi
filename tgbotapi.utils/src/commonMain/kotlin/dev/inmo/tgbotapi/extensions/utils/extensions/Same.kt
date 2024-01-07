package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.abstracts.WithPreviewChat
import dev.inmo.tgbotapi.extensions.utils.usernameChatOrNull
import dev.inmo.tgbotapi.extensions.utils.whenUsernameChat
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.threadId
import dev.inmo.tgbotapi.utils.extensions.threadIdOrNull

/**
 * @return true in case if [this] message is placed in the chat with id == [chatId]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun WithPreviewChat.sameChat(chatId: ChatIdentifier) =
    chat.id == chatId || (chatId is Username && chat.whenUsernameChat {
        it.username == chatId
    } ?: false)

/**
 * @return true in case if [this] message is placed in the [chat]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun WithPreviewChat.sameChat(chat: Chat) =
    sameChat(chat.id) || chat.usernameChatOrNull()?.username?.let { sameChat(it) } ?: false

/**
 * @return true in case if [this] message is placed in the same chat that [other]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun WithPreviewChat.sameChat(other: AccessibleMessage) = sameChat(other.chat)

/**
 * @return true in case if [this] message is from the same chat (with id == [chatId]) and [this] [AccessibleMessage.messageId]
 * equal [messageId] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun AccessibleMessage.sameMessage(
    chatId: ChatIdentifier,
    messageId: MessageId
) = sameChat(chatId) && this.messageId == messageId

/**
 * @return true in case if [this] message is from the same [chat] and [this] [AccessibleMessage.messageId] equal [messageId]
 * identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun AccessibleMessage.sameMessage(
    chat: Chat,
    messageId: MessageId
) = sameChat(chat) && this.messageId == messageId

/**
 * @return true in case if [this] message is the same as [other]. The same here means that these messages from one chat
 * and have equal [AccessibleMessage.messageId] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun AccessibleMessage.sameMessage(other: AccessibleMessage) = sameMessage(other.chat, other.messageId)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is in the chat [chatId] and topic [threadId]. The same here means that these
 * messages from one chat and have equal [AccessibleMessage.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun AccessibleMessage.sameTopic(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sameChat(chatId) && threadIdOrNull == threadId

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is in the chat [chatId] and topic [threadId]. The same here means that these
 * messages from one chat and have equal [AccessibleMessage.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun AccessibleMessage.sameThread(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sameTopic(chatId, threadId)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is from the [chat] and topic [threadId]. The same here means that these
 * messages from one chat and have equal [AccessibleMessage.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun AccessibleMessage.sameTopic(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sameTopic(chat.id, threadId)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is from the [chat] and topic [threadId]. The same here means that these
 * messages from one chat and have equal [AccessibleMessage.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun AccessibleMessage.sameThread(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sameThread(chat.id, threadId)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is from the same chat and topic as [other]. The same here means that these
 * messages from one chat and have equal [AccessibleMessage.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun AccessibleMessage.sameTopic(other: AccessibleMessage) = sameTopic(other.chat, other.threadIdOrNull)

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is in the same topic as the [other]. The same here means that these messages
 * from one chat and have equal [AccessibleMessage.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun AccessibleMessage.sameThread(other: AccessibleMessage) = sameTopic(other)
