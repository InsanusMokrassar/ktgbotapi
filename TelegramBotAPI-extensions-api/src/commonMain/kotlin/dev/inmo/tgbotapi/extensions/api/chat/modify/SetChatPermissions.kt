package dev.inmo.tgbotapi.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.SetChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.setDefaultChatMembersPermissions(
    chatId: ChatIdentifier,
    permissions: ChatPermissions
) = execute(SetChatPermissions(chatId, permissions))

suspend fun TelegramBot.setDefaultChatMembersPermissions(
    chat: PublicChat,
    permissions: ChatPermissions
) = setDefaultChatMembersPermissions(chat.id, permissions)
