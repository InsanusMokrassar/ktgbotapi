package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members.UnbanChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.unbanChatMember(
    chatId: ChatIdentifier,
    userId: UserId
) = execute(UnbanChatMember(chatId, userId))

suspend fun RequestsExecutor.unbanChatMember(
    chat: PublicChat,
    userId: UserId
) = unbanChatMember(chat.id, userId)

suspend fun RequestsExecutor.unbanChatMember(
    chatId: ChatId,
    user: User
) = unbanChatMember(chatId, user.id)

suspend fun RequestsExecutor.unbanChatMember(
    chat: PublicChat,
    user: User
) = unbanChatMember(chat.id, user.id)

