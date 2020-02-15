package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members.GetChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.getChatMember(
    chatId: ChatIdentifier,
    userId: UserId
) = execute(GetChatMember(chatId, userId))

suspend fun RequestsExecutor.getChatMember(
    chat: PublicChat,
    userId: UserId
) = getChatMember(chat.id, userId)

suspend fun RequestsExecutor.getChatMember(
    chatId: ChatId,
    user: User
) = getChatMember(chatId, user.id)

suspend fun RequestsExecutor.getChatMember(
    chat: PublicChat,
    user: User
) = getChatMember(chat.id, user.id)
