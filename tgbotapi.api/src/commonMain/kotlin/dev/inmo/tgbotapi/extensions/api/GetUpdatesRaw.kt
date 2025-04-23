package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.GetUpdatesRaw
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.json.JsonArray

public suspend fun TelegramBot.getRawUpdates(
    offset: UpdateId? = null,
    limit: Int = getUpdatesLimit.last,
    timeout: Seconds? = null,
    allowed_updates: List<String>? = ALL_UPDATES_LIST,
): JsonArray = execute(
    GetUpdatesRaw(
        offset,
        limit,
        timeout,
        allowed_updates,
    ),
)

public suspend fun TelegramBot.getRawUpdates(
    lastUpdate: Update,
    limit: Int = getUpdatesLimit.last,
    timeout: Seconds? = null,
    allowed_updates: List<String>? = ALL_UPDATES_LIST,
): JsonArray = getRawUpdates(
    lastUpdate.updateId + 1,
    limit,
    timeout,
    allowed_updates,
)
