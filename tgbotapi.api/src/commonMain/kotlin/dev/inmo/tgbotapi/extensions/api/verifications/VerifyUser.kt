package dev.inmo.tgbotapi.extensions.api.verifications

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.verifications.VerifyUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.toChatId

public suspend fun TelegramBot.verifyUser(
    userId: UserId,
    description: String? = null,
): Boolean =
    execute(
        VerifyUser(
            userId = userId,
            description = description,
        ),
    )

public suspend fun TelegramBot.verifyUser(
    chat: Chat,
    description: String? = null,
): Boolean =
    verifyUser(
        userId = chat.id.toChatId(),
        description = description,
    )
