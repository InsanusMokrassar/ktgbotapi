package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members.RestrictChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

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

