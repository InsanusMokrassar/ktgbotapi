package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.SetChatTitle
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.setChatTitle(
    chatId: ChatIdentifier,
    title: String
) = execute(SetChatTitle(chatId, title))

suspend fun RequestsExecutor.setChatTitle(
    chat: PublicChat,
    title: String
) = setChatTitle(chat.id, title)
