package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetStarTransactions
import dev.inmo.tgbotapi.types.payments.stars.StarTransactions

public suspend fun TelegramBot.getStarTransactions(
    offset: Int? = null,
    limit: Int? = null,
): StarTransactions = execute(
    GetStarTransactions(
        offset = offset,
        limit = limit,
    ),
)
