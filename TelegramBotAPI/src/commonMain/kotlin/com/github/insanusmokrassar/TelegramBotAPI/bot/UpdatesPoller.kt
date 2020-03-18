package com.github.insanusmokrassar.TelegramBotAPI.bot

import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Deprecated("Deprecated due to more simple way to get updates using TelegramBotAPI-extensions-api")
interface UpdatesPoller : Closeable {
    fun start(scope: CoroutineScope = CoroutineScope(Dispatchers.Default))
}