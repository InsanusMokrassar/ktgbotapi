package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.SetChatDescription
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.setChatDescription(
    chatId: ChatIdentifier,
    description: String
) = execute(SetChatDescription(chatId, description))

suspend fun RequestsExecutor.setChatDescription(
    chat: PublicChat,
    description: String
) = setChatDescription(chat.id, description)
