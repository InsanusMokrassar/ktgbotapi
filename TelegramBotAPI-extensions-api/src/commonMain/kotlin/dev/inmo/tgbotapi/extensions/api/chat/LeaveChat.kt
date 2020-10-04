package dev.inmo.tgbotapi.extensions.api.chat

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.LeaveChat
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.leaveChat(
    chatId: ChatIdentifier
) = execute(LeaveChat(chatId))

suspend fun TelegramBot.leaveChat(
    chat: PublicChat
) = leaveChat(chat.id)
