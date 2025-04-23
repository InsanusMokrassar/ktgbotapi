package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.BanChatMember
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

public suspend fun TelegramBot.banChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    revokeMessages: Boolean? = null,
): Boolean = execute(BanChatMember(chatId, userId, untilDate, revokeMessages))

public suspend fun TelegramBot.banChatMember(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null,
    revokeMessages: Boolean? = null,
): Boolean = banChatMember(chat.id, userId, untilDate, revokeMessages)

public suspend fun TelegramBot.banChatMember(
    chatId: IdChatIdentifier,
    user: User,
    untilDate: TelegramDate? = null,
    revokeMessages: Boolean? = null,
): Boolean = banChatMember(chatId, user.id, untilDate, revokeMessages)

public suspend fun TelegramBot.banChatMember(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null,
    revokeMessages: Boolean? = null,
): Boolean = banChatMember(chat.id, user.id, untilDate, revokeMessages)
