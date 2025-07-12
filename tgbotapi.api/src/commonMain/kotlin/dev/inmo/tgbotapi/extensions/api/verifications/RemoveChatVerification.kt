package dev.inmo.tgbotapi.extensions.api.verifications

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.verifications.RemoveChatVerification
import dev.inmo.tgbotapi.types.ChatIdentifier

public suspend fun TelegramBot.removeChatVerification(
    chatId: ChatIdentifier
): Boolean = execute(
    RemoveChatVerification(chatId)
)