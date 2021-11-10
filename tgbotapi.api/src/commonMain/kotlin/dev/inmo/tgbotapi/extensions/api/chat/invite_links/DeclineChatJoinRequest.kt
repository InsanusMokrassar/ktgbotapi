package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.ApproveChatJoinRequest
import dev.inmo.tgbotapi.requests.chat.invite_links.DeclineChatJoinRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat
import dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate

suspend fun TelegramBot.declineChatJoinRequest(
    chatId: ChatIdentifier,
    userId: UserId
) = execute(DeclineChatJoinRequest(chatId, userId))

suspend fun TelegramBot.declineChatJoinRequest(
    chat: PublicChat,
    userId: UserId
) = declineChatJoinRequest(chat.id, userId)

suspend fun TelegramBot.declineChatJoinRequest(
    chatId: ChatIdentifier,
    user: User
) = declineChatJoinRequest(chatId, user.id)

suspend fun TelegramBot.declineChatJoinRequest(
    chat: PublicChat,
    user: User
) = declineChatJoinRequest(chat.id, user.id)

suspend fun TelegramBot.declineChatJoinRequest(
    chatJoinRequest: ChatJoinRequest
) = declineChatJoinRequest(chatJoinRequest.chat, chatJoinRequest.user)

suspend fun TelegramBot.decline(
    chatJoinRequest: ChatJoinRequest
) = declineChatJoinRequest(chatJoinRequest)

suspend fun TelegramBot.declineChatJoinRequest(
    chatJoinRequestUpdate: ChatJoinRequestUpdate
) = declineChatJoinRequest(chatJoinRequestUpdate.data)
