package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members.GetChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

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
