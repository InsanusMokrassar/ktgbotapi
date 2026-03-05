package dev.inmo.tgbotapi.extensions.api.chat

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.LeaveChat
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.leaveChat(
    chatId: ChatIdentifier
): Unit = execute(LeaveChat(chatId))

public suspend fun TelegramBot.leaveChat(
    chat: PublicChat
): Unit = leaveChat(chat.id)
