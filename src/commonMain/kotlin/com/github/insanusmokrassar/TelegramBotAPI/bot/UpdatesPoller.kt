package com.github.insanusmokrassar.TelegramBotAPI.bot

import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

interface UpdatesPoller : Closeable {
    fun start(scope: CoroutineScope = CoroutineScope(Dispatchers.Default))
}