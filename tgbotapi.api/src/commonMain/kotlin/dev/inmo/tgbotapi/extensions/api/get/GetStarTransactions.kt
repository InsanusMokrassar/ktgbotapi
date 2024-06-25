package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetStarTransactions

suspend fun TelegramBot.getStarTransactions(
    offset: Int? = null,
    limit: Int? = null,
) = execute(
    GetStarTransactions(
        offset = offset,
        limit = limit
    )
)
