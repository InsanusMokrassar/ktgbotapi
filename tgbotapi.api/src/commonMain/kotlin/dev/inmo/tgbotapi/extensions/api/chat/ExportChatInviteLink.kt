package dev.inmo.tgbotapi.extensions.api.chat

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.ExportChatInviteLink
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

suspend fun TelegramBot.exportChatInviteLink(
    chatId: ChatIdentifier
) = execute(ExportChatInviteLink(chatId))

suspend fun TelegramBot.exportChatInviteLink(
    chat: PublicChat
) = exportChatInviteLink(chat.id)
