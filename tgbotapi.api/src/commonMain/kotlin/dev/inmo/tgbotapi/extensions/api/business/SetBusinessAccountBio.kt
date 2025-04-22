package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.SetBusinessAccountBio
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

public suspend fun TelegramBot.setBusinessAccountBio(
    businessConnectionId: BusinessConnectionId,
    bio: String,
): Boolean =
    execute(
        SetBusinessAccountBio(businessConnectionId, bio),
    ) 
