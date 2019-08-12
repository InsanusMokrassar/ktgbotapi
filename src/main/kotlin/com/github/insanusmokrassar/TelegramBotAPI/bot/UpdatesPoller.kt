package com.github.insanusmokrassar.TelegramBotAPI.bot

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.io.core.Closeable

interface UpdatesPoller : Closeable {
    fun start(scope: CoroutineScope = CoroutineScope(Dispatchers.Default))
}