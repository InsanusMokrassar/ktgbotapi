package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetChatPermissions
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.setDefaultChatMembersPermissions(
    chatId: ChatIdentifier,
    permissions: ChatPermissions,
    useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it }
): Boolean = execute(SetChatPermissions(chatId, permissions, useIndependentChatPermissions))

public suspend fun TelegramBot.setDefaultChatMembersPermissions(
    chat: PublicChat,
    permissions: ChatPermissions,
    useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it }
): Boolean = setDefaultChatMembersPermissions(chat.id, permissions, useIndependentChatPermissions)
