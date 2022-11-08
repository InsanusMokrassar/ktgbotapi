package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.utils.extensions.threadIdOrNull

/**
 * @return true in case if [this] message is placed in the same chat that [other]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameChat(other: Message) = chat.id == other.chat.id

/**
 * @return true in case if [this] message is the same as [other]. The same here means that these messages from one chat
 * and have equal [Message.messageId] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameMessage(other: Message) = sameChat(other) && messageId == other.messageId

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is in the same topic as the [other]. The same here means that these messages
 * from one chat and have equal [Message.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameTopic(other: Message) = sameChat(other) && threadIdOrNull == other.threadIdOrNull

/**
 * Thread is the same thing that topic
 *
 * @return true in case if [this] message is in the same topic as the [other]. The same here means that these messages
 * from one chat and have equal [Message.threadIdOrNull] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameThread(other: Message) = sameChat(other) && threadIdOrNull == other.threadIdOrNull
