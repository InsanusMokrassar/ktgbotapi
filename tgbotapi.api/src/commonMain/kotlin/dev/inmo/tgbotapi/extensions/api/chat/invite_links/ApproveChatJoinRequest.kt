package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.ApproveChatJoinRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate

suspend fun TelegramBot.approveChatJoinRequest(
    chatId: ChatIdentifier,
    userId: UserId
) = execute(ApproveChatJoinRequest(chatId, userId))

suspend fun TelegramBot.approveChatJoinRequest(
    chat: PublicChat,
    userId: UserId
) = approveChatJoinRequest(chat.id, userId)

suspend fun TelegramBot.approveChatJoinRequest(
    chatId: ChatIdentifier,
    user: User
) = approveChatJoinRequest(chatId, user.id)

suspend fun TelegramBot.approveChatJoinRequest(
    chat: PublicChat,
    user: User
) = approveChatJoinRequest(chat.id, user.id)

suspend fun TelegramBot.approveChatJoinRequest(
    chatJoinRequest: ChatJoinRequest
) = approveChatJoinRequest(chatJoinRequest.chat, chatJoinRequest.user)

suspend fun TelegramBot.approve(
    chatJoinRequest: ChatJoinRequest
) = approveChatJoinRequest(chatJoinRequest)

suspend fun TelegramBot.approveChatJoinRequest(
    chatJoinRequestUpdate: ChatJoinRequestUpdate
) = approveChatJoinRequest(chatJoinRequestUpdate.data)
