package com.github.insanusmokrassar.TelegramBotAPI.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.GetUpdates
import com.github.insanusmokrassar.TelegramBotAPI.types.ALL_UPDATES_LIST
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

suspend fun RequestsExecutor.getUpdates(
    offset: UpdateIdentifier? = null,
    limit: Int? = null,
    timeout: Int? = null,
    allowed_updates: List<String>? = ALL_UPDATES_LIST
) = execute(
    GetUpdates(
        offset, limit, timeout, allowed_updates
    )
)

suspend fun RequestsExecutor.getUpdates(
    lastUpdate: Update,
    limit: Int? = null,
    timeout: Int? = null,
    allowed_updates: List<String>? = ALL_UPDATES_LIST
) = getUpdates(
    lastUpdate.updateId + 1, limit, timeout, allowed_updates
)
