package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.GetBusinessAccountStarBalance
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.payments.stars.StarAmount

public suspend fun TelegramBot.getBusinessAccountStarBalance(
    businessConnectionId: BusinessConnectionId
): StarAmount = execute(
    GetBusinessAccountStarBalance(businessConnectionId)
) 