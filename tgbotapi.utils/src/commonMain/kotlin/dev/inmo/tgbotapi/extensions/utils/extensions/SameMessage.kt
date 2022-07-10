package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.types.message.abstracts.Message

/**
 * @return true in case if [this] message is the same as [other]. The same here means that these messages from one chat
 * and have equal [Message.messageId] identifier
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Message.sameMessage(other: Message) = chat.id == other.chat.id && messageId == other.messageId
