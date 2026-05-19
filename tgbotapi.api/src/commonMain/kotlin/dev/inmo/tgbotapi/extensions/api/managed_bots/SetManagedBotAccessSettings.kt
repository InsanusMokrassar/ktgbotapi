package dev.inmo.tgbotapi.extensions.api.managed_bots

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.managed_bots.SetManagedBotAccessSettings
import dev.inmo.tgbotapi.types.ChatId

public suspend fun TelegramBot.setManagedBotAccessSettings(
    userId: ChatId,
    addedUserIds: List<ChatId>? = null
): Unit = execute(
    SetManagedBotAccessSettings(
        userId = userId,
        addedUserIds = addedUserIds
    )
)
