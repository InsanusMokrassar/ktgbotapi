package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetChatPermissions
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.PublicChat

suspend fun TelegramBot.setChatMembersPermissions(
    chatId: ChatIdentifier,
    permissions: ChatPermissions,
    useIndependentChatPermissions: Boolean? = null,
) = execute(SetChatPermissions(chatId, permissions))

suspend fun TelegramBot.setChatMembersPermissions(
    chat: PublicChat,
    permissions: ChatPermissions,
    useIndependentChatPermissions: Boolean? = null,
) = setChatMembersPermissions(chat.id, permissions)
