package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdates(chatId: ChatId): Flow<T> = filter {
    it.data.chat.id == chatId
}
fun <T : BaseMessageUpdate> Flow<T>.filterBaseMessageUpdates(chat: Chat): Flow<T> = filterBaseMessageUpdates(chat.id)


fun <T : SentMediaGroupUpdate> Flow<T>.filterSentMediaGroupUpdates(chatId: ChatId): Flow<T> = filter {
    it.data.first().chat.id == chatId
}
fun <T : SentMediaGroupUpdate> Flow<T>.filterSentMediaGroupUpdates(chat: Chat): Flow<T> = filterSentMediaGroupUpdates(chat.id)
