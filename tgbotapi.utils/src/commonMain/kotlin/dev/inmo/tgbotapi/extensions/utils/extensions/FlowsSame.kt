package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.abstracts.WithPreviewChat
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.transform

/**
 * Will pass only those [T] which have [sameChat] as [chatId]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T : WithPreviewChat> Flow<T>.fromChat(chatId: ChatIdentifier): Flow<T> = filter { it.sameChat(chatId) }

/**
 * Will pass only those [T] which have [sameChat] as [chatId]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T : WithPreviewChat> Flow<T>.fromChat(chat: Chat): Flow<T> = fromChat(chat.id)

/**
 * @return [Flow] with the [FromUser.user] field [User.id] the same as [userId]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T : FromUser> Flow<T>.fromUser(userId: UserId): Flow<T> = filter { it.user.id == userId }

/**
 * @return [Flow] with the [FromUser.user] field [User.id] the same as [user] [User.id]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T : FromUser> Flow<T>.fromUser(user: User): Flow<T> = fromUser(user.id)

