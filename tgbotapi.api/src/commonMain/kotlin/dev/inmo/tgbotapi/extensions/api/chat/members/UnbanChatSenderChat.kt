package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.UnbanChatSenderChat
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.unbanChatSenderChat(
    chatId: ChatIdentifier,
    senderChatId: IdChatIdentifier,
): Boolean = execute(UnbanChatSenderChat(chatId, senderChatId))

public suspend fun TelegramBot.unbanChatSenderChat(
    chat: PublicChat,
    senderChatId: IdChatIdentifier,
): Boolean = unbanChatSenderChat(chat.id, senderChatId)

public suspend fun TelegramBot.unbanChatSenderChat(
    chatId: IdChatIdentifier,
    senderChat: PublicChat,
): Boolean = unbanChatSenderChat(chatId, senderChat.id)

public suspend fun TelegramBot.unbanChatSenderChat(
    chat: PublicChat,
    senderChat: PublicChat,
): Boolean = unbanChatSenderChat(chat.id, senderChat)
