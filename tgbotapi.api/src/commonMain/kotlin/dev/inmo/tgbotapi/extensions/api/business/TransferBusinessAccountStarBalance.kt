package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.TransferBusinessAccountStarBalance
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

public suspend fun TelegramBot.transferBusinessAccountStarBalance(
    businessConnectionId: BusinessConnectionId,
    amount: Int,
): Boolean =
    execute(
        TransferBusinessAccountStarBalance(businessConnectionId, amount),
    ) 
