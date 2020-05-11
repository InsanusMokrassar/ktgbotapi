package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.SentMediaGroupUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

/**
 * [Flow.filter] incoming [BaseMessageUpdate]s by their [ChatId]
 */
fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdates(chatId: ChatId): Flow<T> = filter {
    it.data.chat.id == chatId
}
/**
 * [Flow.filter] incoming [BaseMessageUpdate]s by their [ChatId] using [Chat.id] of [chat]
 */
fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdates(chat: Chat): Flow<T> = filterBaseMessageUpdates(chat.id)


/**
 * [Flow.filter] incoming [SentMediaGroupUpdate]s by their [ChatId]
 */
fun <T : SentMediaGroupUpdate> Flow<T>.filterSentMediaGroupUpdates(chatId: ChatId): Flow<T> = filter {
    it.data.first().chat.id == chatId
}
/**
 * [Flow.filter] incoming [SentMediaGroupUpdate]s by their [ChatId] using [Chat.id] of [chat]
 */
fun <T : SentMediaGroupUpdate> Flow<T>.filterSentMediaGroupUpdates(chat: Chat): Flow<T> = filterSentMediaGroupUpdates(chat.id)
