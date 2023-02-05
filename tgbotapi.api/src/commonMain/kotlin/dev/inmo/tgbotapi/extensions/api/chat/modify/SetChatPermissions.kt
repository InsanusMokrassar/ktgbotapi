package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetChatPermissions
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.PublicChat

suspend fun TelegramBot.setDefaultChatMembersPermissions(
    chatId: ChatIdentifier,
    permissions: ChatPermissions,
    useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it }
) = execute(SetChatPermissions(chatId, permissions, useIndependentChatPermissions))

suspend fun TelegramBot.setDefaultChatMembersPermissions(
    chat: PublicChat,
    permissions: ChatPermissions,
    useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it }
) = setDefaultChatMembersPermissions(chat.id, permissions, useIndependentChatPermissions)
