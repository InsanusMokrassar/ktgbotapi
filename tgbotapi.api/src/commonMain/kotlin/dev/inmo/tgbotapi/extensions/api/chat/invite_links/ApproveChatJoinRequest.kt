package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.ApproveChatJoinRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate

public suspend fun TelegramBot.approveChatJoinRequest(
    chatId: ChatIdentifier,
    userId: UserId,
): Boolean = execute(ApproveChatJoinRequest(chatId, userId))

public suspend fun TelegramBot.approveChatJoinRequest(
    chat: PublicChat,
    userId: UserId,
): Boolean = approveChatJoinRequest(chat.id, userId)

public suspend fun TelegramBot.approveChatJoinRequest(
    chatId: ChatIdentifier,
    user: User,
): Boolean = approveChatJoinRequest(chatId, user.id)

public suspend fun TelegramBot.approveChatJoinRequest(
    chat: PublicChat,
    user: User,
): Boolean = approveChatJoinRequest(chat.id, user.id)

public suspend fun TelegramBot.approveChatJoinRequest(chatJoinRequest: ChatJoinRequest): Boolean = approveChatJoinRequest(chatJoinRequest.chat, chatJoinRequest.user)

public suspend fun TelegramBot.approve(chatJoinRequest: ChatJoinRequest): Boolean = approveChatJoinRequest(chatJoinRequest)

public suspend fun TelegramBot.approveChatJoinRequest(chatJoinRequestUpdate: ChatJoinRequestUpdate): Boolean = approveChatJoinRequest(chatJoinRequestUpdate.data)
