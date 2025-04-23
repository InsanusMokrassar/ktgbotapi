package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.SetBusinessAccountName
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

public suspend fun TelegramBot.setBusinessAccountName(
    businessConnectionId: BusinessConnectionId,
    firstName: String,
    lastName: String? = null,
): Boolean = execute(
    SetBusinessAccountName(businessConnectionId, firstName, lastName),
)
