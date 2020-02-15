package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get.GetChat
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat

suspend fun RequestsExecutor.getChat(
    chatId: ChatIdentifier
) = execute(GetChat(chatId))

suspend fun RequestsExecutor.getChat(
    chat: Chat
) = getChat(chat.id)
