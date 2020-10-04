package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.RestrictChatMember
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.restrictChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions()
) = execute(RestrictChatMember(chatId, userId, untilDate, permissions))

suspend fun TelegramBot.restrictChatMember(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions()
) = restrictChatMember(chat.id, userId, untilDate, permissions)

suspend fun TelegramBot.restrictChatMember(
    chatId: ChatId,
    user: User,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions()
) = restrictChatMember(chatId, user.id, untilDate, permissions)

suspend fun TelegramBot.restrictChatMember(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions()
) = restrictChatMember(chat.id, user.id, untilDate, permissions)

