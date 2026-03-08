package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.DeclineChatJoinRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate

public suspend fun TelegramBot.declineChatJoinRequest(
    chatId: ChatIdentifier,
    userId: UserId
): Unit = execute(DeclineChatJoinRequest(chatId, userId))

public suspend fun TelegramBot.declineChatJoinRequest(
    chat: PublicChat,
    userId: UserId
): Unit = declineChatJoinRequest(chat.id, userId)

public suspend fun TelegramBot.declineChatJoinRequest(
    chatId: ChatIdentifier,
    user: User
): Unit = declineChatJoinRequest(chatId, user.id)

public suspend fun TelegramBot.declineChatJoinRequest(
    chat: PublicChat,
    user: User
): Unit = declineChatJoinRequest(chat.id, user.id)

public suspend fun TelegramBot.declineChatJoinRequest(
    chatJoinRequest: ChatJoinRequest
): Unit = declineChatJoinRequest(chatJoinRequest.chat, chatJoinRequest.user)

public suspend fun TelegramBot.decline(
    chatJoinRequest: ChatJoinRequest
): Unit = declineChatJoinRequest(chatJoinRequest)

public suspend fun TelegramBot.declineChatJoinRequest(
    chatJoinRequestUpdate: ChatJoinRequestUpdate
): Unit = declineChatJoinRequest(chatJoinRequestUpdate.data)
