package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetChatPermissions
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.setDefaultChatMembersPermissions(
    chatId: ChatIdentifier,
    permissions: ChatPermissions
) = execute(SetChatPermissions(chatId, permissions))

suspend fun TelegramBot.setDefaultChatMembersPermissions(
    chat: PublicChat,
    permissions: ChatPermissions
) = setDefaultChatMembersPermissions(chat.id, permissions)
