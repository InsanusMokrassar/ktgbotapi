package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.GetUpdates
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.update.abstracts.Update

suspend fun TelegramBot.getUpdates(
    offset: UpdateId? = null,
    limit: Int = getUpdatesLimit.last,
    timeout: Seconds? = null,
    allowed_updates: List<String>? = ALL_UPDATES_LIST
) = execute(
    GetUpdates(
        offset, limit, timeout, allowed_updates
    )
)

suspend fun TelegramBot.getUpdates(
    lastUpdate: Update,
    limit: Int = getUpdatesLimit.last,
    timeout: Seconds? = null,
    allowed_updates: List<String>? = ALL_UPDATES_LIST
) = getUpdates(
    lastUpdate.updateId + 1, limit, timeout, allowed_updates
)
