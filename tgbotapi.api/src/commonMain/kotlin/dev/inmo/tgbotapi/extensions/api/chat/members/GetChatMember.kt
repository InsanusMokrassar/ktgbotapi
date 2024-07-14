package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.GetChatMember
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.member.ChatMember

public suspend fun TelegramBot.getChatMember(
    chatId: ChatIdentifier,
    userId: UserId
): ChatMember = execute(GetChatMember(chatId, userId))

public suspend fun TelegramBot.getChatMember(
    chat: PublicChat,
    userId: UserId
): ChatMember = getChatMember(chat.id, userId)

public suspend fun TelegramBot.getChatMember(
    chatId: IdChatIdentifier,
    user: User
): ChatMember = getChatMember(chatId, user.id)

public suspend fun TelegramBot.getChatMember(
    chat: PublicChat,
    user: User
): ChatMember = getChatMember(chat.id, user.id)
