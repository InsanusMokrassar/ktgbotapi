package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

fun <T : BaseMessageUpdate> Flow<T>.filterByChatId(chatId: ChatId): Flow<T> = filter {
    it.data.chat.id == chatId
}
fun <T : BaseMessageUpdate> Flow<T>.filterByChat(chat: Chat): Flow<T> = filterByChatId(chat.id)


fun <T : SentMediaGroupUpdate> Flow<T>.filterByChatId(chatId: ChatId): Flow<T> = filter {
    it.data.first().chat.id == chatId
}
fun <T : SentMediaGroupUpdate> Flow<T>.filterByChatId(chat: Chat): Flow<T> = filterByChatId(chat.id)


fun <T : EditMediaGroupUpdate> Flow<T>.filterByChatId(chatId: ChatId): Flow<T> = filter {
    it.data.chat.id == chatId
}
fun <T : EditMediaGroupUpdate> Flow<T>.filterByChatId(chat: Chat): Flow<T> = filterByChatId(chat.id)
