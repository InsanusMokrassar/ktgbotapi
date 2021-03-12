package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.KickChatMember
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.kickChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    revokeMessages: Boolean? = null
) = execute(KickChatMember(chatId, userId, untilDate, revokeMessages))

suspend fun TelegramBot.kickChatMember(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null,
    revokeMessages: Boolean? = null
) = kickChatMember(chat.id, userId, untilDate, revokeMessages)

suspend fun TelegramBot.kickChatMember(
    chatId: ChatId,
    user: User,
    untilDate: TelegramDate? = null,
    revokeMessages: Boolean? = null
) = kickChatMember(chatId, user.id, untilDate, revokeMessages)

suspend fun TelegramBot.kickChatMember(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null,
    revokeMessages: Boolean? = null
) = kickChatMember(chat.id, user.id, untilDate, revokeMessages)
