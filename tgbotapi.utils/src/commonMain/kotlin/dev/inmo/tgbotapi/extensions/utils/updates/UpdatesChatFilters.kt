package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

/**
 * [Flow.filter] incoming [BaseMessageUpdate]s by their [ChatId]
 */
fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdatesByChatId(chatId: ChatId): Flow<T> = filter { it.data.chat.id == chatId }
/**
 * [Flow.filter] incoming [BaseMessageUpdate]s by their [ChatId] using [Chat.id] of [chat]
 */
fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdatesByChat(chat: Chat): Flow<T> = filterBaseMessageUpdatesByChatId(chat.id)
