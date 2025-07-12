package dev.inmo.tgbotapi.extensions.api.verifications

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.verifications.RemoveUserVerification
import dev.inmo.tgbotapi.types.UserId

public suspend fun TelegramBot.removeUserVerification(
    userId: UserId
): Boolean = execute(
    RemoveUserVerification(userId)
)