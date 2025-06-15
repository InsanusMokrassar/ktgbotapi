package dev.inmo.tgbotapi.bot

import dev.inmo.kslog.common.KSLog
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import io.ktor.utils.io.core.Closeable

/**
 * Interface for making requests to Telegram Bot API. Currently, there is only one built-in implementation -
 * [dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutor]
 *
 * @see Request
 * @see dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutor
 */
interface RequestsExecutor : Closeable {
    val Log: KSLog
        get() = DefaultKTgBotAPIKSLog
    /**
     * Unsafe execution of incoming [request]. Can throw almost any exception. So, it is better to use
     * something like [dev.inmo.tgbotapi.extensions.utils.shortcuts.executeAsync] or
     * [dev.inmo.tgbotapi.extensions.utils.shortcuts.executeUnsafe]
     *
     * @throws Exception
     */
    suspend fun <T : Any> execute(request: Request<T>): T
}

typealias TelegramBot = RequestsExecutor
