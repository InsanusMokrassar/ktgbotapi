package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.SetChatAdministratorCustomTitle
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

suspend fun TelegramBot.setChatAdministratorCustomTitle(
    chatId: ChatId,
    userId: UserId,
    customTitle: String
) = execute(SetChatAdministratorCustomTitle(chatId, userId, customTitle))

suspend fun TelegramBot.setChatAdministratorCustomTitle(
    chat: PublicChat,
    userId: UserId,
    customTitle: String
) = setChatAdministratorCustomTitle(chat.id, userId, customTitle)

suspend fun TelegramBot.setChatAdministratorCustomTitle(
    chatId: ChatId,
    user: User,
    customTitle: String
) = setChatAdministratorCustomTitle(chatId, user.id, customTitle)

suspend fun TelegramBot.setChatAdministratorCustomTitle(
    chat: PublicChat,
    user: User,
    customTitle: String
) = setChatAdministratorCustomTitle(chat.id, user.id, customTitle)
