package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.unbanChatSenderChat(
    chatId: ChatIdentifier,
    senderChatId: ChatId
) = execute(UnbanChatSenderChat(chatId, senderChatId))

suspend fun TelegramBot.unbanChatSenderChat(
    chat: PublicChat,
    senderChatId: ChatId
) = unbanChatSenderChat(chat.id, senderChatId)

suspend fun TelegramBot.unbanChatSenderChat(
    chatId: ChatId,
    senderChat: PublicChat
) = unbanChatSenderChat(chatId, senderChat.id)

suspend fun TelegramBot.unbanChatSenderChat(
    chat: PublicChat,
    senderChat: PublicChat,
) = unbanChatSenderChat(chat.id, senderChat)
