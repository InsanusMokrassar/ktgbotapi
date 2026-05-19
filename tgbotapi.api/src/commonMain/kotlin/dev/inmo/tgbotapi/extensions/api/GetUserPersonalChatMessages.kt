package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.GetUserPersonalChatMessages
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage

public suspend fun TelegramBot.getUserPersonalChatMessages(
    userId: ChatId,
    limit: Int
): List<ContentMessage<*>> = execute(
    GetUserPersonalChatMessages(userId = userId, limit = limit)
)
