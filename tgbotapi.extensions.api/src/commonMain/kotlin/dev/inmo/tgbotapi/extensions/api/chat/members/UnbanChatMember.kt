package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.UnbanChatMember
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.unbanChatMember(
    chatId: ChatIdentifier,
    userId: UserId
) = execute(UnbanChatMember(chatId, userId))

suspend fun TelegramBot.unbanChatMember(
    chat: PublicChat,
    userId: UserId
) = unbanChatMember(chat.id, userId)

suspend fun TelegramBot.unbanChatMember(
    chatId: ChatId,
    user: User
) = unbanChatMember(chatId, user.id)

suspend fun TelegramBot.unbanChatMember(
    chat: PublicChat,
    user: User
) = unbanChatMember(chat.id, user.id)

