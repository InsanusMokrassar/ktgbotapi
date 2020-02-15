package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.SetChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.setDefaultChatMembersPermissions(
    chatId: ChatIdentifier,
    permissions: ChatPermissions
) = execute(SetChatPermissions(chatId, permissions))

suspend fun RequestsExecutor.setDefaultChatMembersPermissions(
    chat: PublicChat,
    permissions: ChatPermissions
) = setDefaultChatMembersPermissions(chat.id, permissions)
