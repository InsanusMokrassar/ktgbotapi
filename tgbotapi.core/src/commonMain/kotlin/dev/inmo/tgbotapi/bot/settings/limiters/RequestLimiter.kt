package dev.inmo.tgbotapi.bot.settings.limiters

import dev.inmo.tgbotapi.requests.abstracts.Request

interface RequestLimiter {
    /**
     * Use limit for working of block (like delay between or after, for example)
     */
    suspend fun <T> limit(block: suspend () -> T): T

    suspend fun <T : Any> limit(
        request: Request<T>,
        block: suspend () -> T,
    ) = limit(block)
}
