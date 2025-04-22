package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.UnbanChatMember
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

public suspend fun TelegramBot.unbanChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    onlyIfBanned: Boolean? = null,
): Boolean = execute(UnbanChatMember(chatId, userId, onlyIfBanned))

public suspend fun TelegramBot.unbanChatMember(
    chat: PublicChat,
    userId: UserId,
    onlyIfBanned: Boolean? = null,
): Boolean = unbanChatMember(chat.id, userId, onlyIfBanned)

public suspend fun TelegramBot.unbanChatMember(
    chatId: IdChatIdentifier,
    user: User,
    onlyIfBanned: Boolean? = null,
): Boolean = unbanChatMember(chatId, user.id, onlyIfBanned)

public suspend fun TelegramBot.unbanChatMember(
    chat: PublicChat,
    user: User,
    onlyIfBanned: Boolean? = null,
): Boolean = unbanChatMember(chat.id, user.id, onlyIfBanned)
