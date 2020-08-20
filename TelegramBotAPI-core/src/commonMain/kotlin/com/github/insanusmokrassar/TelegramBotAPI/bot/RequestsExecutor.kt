package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import io.ktor.utils.io.core.Closeable

/**
 * Interface for making requests to Telegram Bot API. Currently, there is only one built-in implementation -
 * [com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorRequestsExecutor]
 *
 * @see Request
 * @see com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorRequestsExecutor
 */
interface RequestsExecutor : Closeable {
    /**
     * Unsafe execution of incoming [request]. Can throw almost any exception. So, it is better to use
     * something like [com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.shortcuts.executeAsync] or
     * [com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.shortcuts.executeUnsafe]
     *
     * @throws Exception
     */
    suspend fun <T : Any> execute(request: Request<T>): T
}

typealias TelegramBot = RequestsExecutor
