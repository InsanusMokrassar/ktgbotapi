package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.SetChatMemberTag
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

public suspend fun TelegramBot.setChatMemberTag(
    chatId: ChatIdentifier,
    userId: UserId,
    tag: UserTag? = null
): Unit = execute(SetChatMemberTag(chatId, userId, tag))

public suspend fun TelegramBot.setChatMemberTag(
    chat: PublicChat,
    userId: UserId,
    tag: UserTag? = null
): Unit = setChatMemberTag(chat.id, userId, tag)

public suspend fun TelegramBot.setChatMemberTag(
    chatId: ChatIdentifier,
    user: User,
    tag: UserTag? = null
): Unit = setChatMemberTag(chatId, user.id, tag)

public suspend fun TelegramBot.setChatMemberTag(
    chat: PublicChat,
    user: User,
    tag: UserTag? = null
): Unit = setChatMemberTag(chat.id, user.id, tag)
