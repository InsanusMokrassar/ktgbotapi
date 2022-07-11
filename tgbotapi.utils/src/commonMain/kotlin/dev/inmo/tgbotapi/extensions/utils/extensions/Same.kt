package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.types.message.abstracts.Message

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
