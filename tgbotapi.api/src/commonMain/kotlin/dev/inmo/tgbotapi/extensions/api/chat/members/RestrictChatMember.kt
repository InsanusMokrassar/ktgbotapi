package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.RestrictChatMember
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

public suspend fun TelegramBot.restrictChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions(),
    useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it },
): Boolean = execute(RestrictChatMember(chatId, userId, untilDate, permissions, useIndependentChatPermissions))

public suspend fun TelegramBot.restrictChatMember(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions(),
    useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it },
): Boolean = restrictChatMember(chat.id, userId, untilDate, permissions, useIndependentChatPermissions)

public suspend fun TelegramBot.restrictChatMember(
    chatId: IdChatIdentifier,
    user: User,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions(),
    useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it },
): Boolean = restrictChatMember(chatId, user.id, untilDate, permissions, useIndependentChatPermissions)

public suspend fun TelegramBot.restrictChatMember(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions(),
    useIndependentChatPermissions: Boolean? = permissions.isGranular.takeIf { it },
): Boolean = restrictChatMember(chat.id, user.id, untilDate, permissions, useIndependentChatPermissions)
