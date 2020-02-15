package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members.SetChatAdministratorCustomTitle
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.setChatAdministratorCustomTitle(
    chatId: ChatId,
    userId: UserId,
    customTitle: String
) = execute(SetChatAdministratorCustomTitle(chatId, userId, customTitle))

suspend fun RequestsExecutor.setChatAdministratorCustomTitle(
    chat: PublicChat,
    userId: UserId,
    customTitle: String
) = setChatAdministratorCustomTitle(chat.id, userId, customTitle)

suspend fun RequestsExecutor.setChatAdministratorCustomTitle(
    chatId: ChatId,
    user: User,
    customTitle: String
) = setChatAdministratorCustomTitle(chatId, user.id, customTitle)

suspend fun RequestsExecutor.setChatAdministratorCustomTitle(
    chat: PublicChat,
    user: User,
    customTitle: String
) = setChatAdministratorCustomTitle(chat.id, user.id, customTitle)