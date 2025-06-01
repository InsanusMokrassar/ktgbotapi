package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.TransferBusinessAccountStars
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

public suspend fun TelegramBot.transferBusinessAccountStars(
    businessConnectionId: BusinessConnectionId,
    amount: Int
): Boolean = execute(
    TransferBusinessAccountStars(businessConnectionId, amount)
) 