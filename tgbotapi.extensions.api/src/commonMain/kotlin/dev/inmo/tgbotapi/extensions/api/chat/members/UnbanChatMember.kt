package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.UnbanChatMember
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.unbanChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    onlyIfBanned: Boolean? = null
) = execute(UnbanChatMember(chatId, userId, onlyIfBanned))

suspend fun TelegramBot.unbanChatMember(
    chat: PublicChat,
    userId: UserId,
    onlyIfBanned: Boolean? = null
) = unbanChatMember(chat.id, userId, onlyIfBanned)

suspend fun TelegramBot.unbanChatMember(
    chatId: ChatId,
    user: User,
    onlyIfBanned: Boolean? = null
) = unbanChatMember(chatId, user.id, onlyIfBanned)

suspend fun TelegramBot.unbanChatMember(
    chat: PublicChat,
    user: User,
    onlyIfBanned: Boolean? = null
) = unbanChatMember(chat.id, user.id, onlyIfBanned)

