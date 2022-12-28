package dev.inmo.tgbotapi.bot.settings.limiters

import dev.inmo.tgbotapi.bot.exceptions.TooMuchRequestsException
import kotlinx.coroutines.delay

/**
 * Simple limiter which will lock any request when [TooMuchRequestsExceptions] is thrown and rerun request after lock time
 */
object ExceptionsOnlyLimiter : RequestLimiter {
    override suspend fun <T> limit(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: TooMuchRequestsException) {
            delay(e.retryAfter.leftToRetry)
            limit(block)
        }
    }
}
