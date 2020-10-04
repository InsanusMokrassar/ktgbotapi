package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.ExportChatInviteLink
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.exportChatInviteLink(
    chatId: ChatIdentifier
) = execute(ExportChatInviteLink(chatId))

suspend fun TelegramBot.exportChatInviteLink(
    chat: PublicChat
) = exportChatInviteLink(chat.id)
