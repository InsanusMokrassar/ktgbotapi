package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetUserChatBoosts
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.boosts.UserChatBoosts
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.getUserChatBoosts(
    chatId: ChatIdentifier,
    userId: UserId
): UserChatBoosts = execute(
    GetUserChatBoosts(chatId = chatId, userId = userId)
)

public suspend fun TelegramBot.getUserChatBoosts(
    chat: Chat,
    userId: UserId
): UserChatBoosts = getUserChatBoosts(chatId = chat.id, userId = userId)