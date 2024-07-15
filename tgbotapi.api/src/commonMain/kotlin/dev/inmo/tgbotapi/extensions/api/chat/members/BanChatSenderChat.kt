package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.BanChatSenderChat
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.banChatSenderChat(
    chatId: ChatIdentifier,
    senderChatId: IdChatIdentifier
): Boolean = execute(BanChatSenderChat(chatId, senderChatId))

public suspend fun TelegramBot.banChatSenderChat(
    chat: PublicChat,
    senderChatId: IdChatIdentifier
): Boolean = banChatSenderChat(chat.id, senderChatId)

public suspend fun TelegramBot.banChatSenderChat(
    chatId: IdChatIdentifier,
    senderChat: PublicChat
): Boolean = banChatSenderChat(chatId, senderChat.id)

public suspend fun TelegramBot.banChatSenderChat(
    chat: PublicChat,
    senderChat: PublicChat,
): Boolean = banChatSenderChat(chat.id, senderChat)
