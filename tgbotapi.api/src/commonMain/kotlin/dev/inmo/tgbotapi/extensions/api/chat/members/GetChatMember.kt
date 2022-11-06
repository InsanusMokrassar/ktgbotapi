package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.GetChatMember
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

suspend fun TelegramBot.getChatMember(
    chatId: ChatIdentifier,
    userId: UserId
) = execute(GetChatMember(chatId, userId))

suspend fun TelegramBot.getChatMember(
    chat: PublicChat,
    userId: UserId
) = getChatMember(chat.id, userId)

suspend fun TelegramBot.getChatMember(
    chatId: ChatId,
    user: User
) = getChatMember(chatId, user.id)

suspend fun TelegramBot.getChatMember(
    chat: PublicChat,
    user: User
) = getChatMember(chat.id, user.id)
