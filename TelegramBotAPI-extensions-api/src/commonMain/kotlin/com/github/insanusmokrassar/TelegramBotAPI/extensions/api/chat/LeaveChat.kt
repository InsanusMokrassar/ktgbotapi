package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.LeaveChat
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.leaveChat(
    chatId: ChatIdentifier
) = execute(LeaveChat(chatId))

suspend fun RequestsExecutor.leaveChat(
    chat: PublicChat
) = leaveChat(chat.id)
