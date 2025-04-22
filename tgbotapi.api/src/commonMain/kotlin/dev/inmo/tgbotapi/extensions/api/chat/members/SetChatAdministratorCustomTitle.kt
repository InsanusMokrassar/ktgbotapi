package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.SetChatAdministratorCustomTitle
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

public suspend fun TelegramBot.setChatAdministratorCustomTitle(
    chatId: IdChatIdentifier,
    userId: UserId,
    customTitle: String,
): Boolean = execute(SetChatAdministratorCustomTitle(chatId, userId, customTitle))

public suspend fun TelegramBot.setChatAdministratorCustomTitle(
    chat: PublicChat,
    userId: UserId,
    customTitle: String,
): Boolean = setChatAdministratorCustomTitle(chat.id, userId, customTitle)

public suspend fun TelegramBot.setChatAdministratorCustomTitle(
    chatId: IdChatIdentifier,
    user: User,
    customTitle: String,
): Boolean = setChatAdministratorCustomTitle(chatId, user.id, customTitle)

public suspend fun TelegramBot.setChatAdministratorCustomTitle(
    chat: PublicChat,
    user: User,
    customTitle: String,
): Boolean = setChatAdministratorCustomTitle(chat.id, user.id, customTitle)
