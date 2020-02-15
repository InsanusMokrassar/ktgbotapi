package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get.GetChatMembersCount
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.getChatMembersCount(
    chatId: ChatIdentifier
) = execute(GetChatMembersCount(chatId))

suspend fun RequestsExecutor.getChatMembersCount(
    chat: PublicChat
) = getChatMembersCount(chat.id)
