package dev.inmo.tgbotapi.extensions.api.chat

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.ExportChatInviteLink
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.exportChatInviteLink(chatId: ChatIdentifier): String = execute(ExportChatInviteLink(chatId))

public suspend fun TelegramBot.exportChatInviteLink(chat: PublicChat): String = exportChatInviteLink(chat.id)
