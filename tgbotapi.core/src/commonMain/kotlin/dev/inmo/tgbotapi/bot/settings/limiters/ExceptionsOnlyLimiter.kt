package dev.inmo.tgbotapi.bot.settings.limiters

import dev.inmo.micro_utils.coroutines.runCatchingSafely
import dev.inmo.tgbotapi.bot.exceptions.TooMuchRequestsException
import kotlinx.coroutines.delay

/**
 * Simple limiter which will lock any request when [TooMuchRequestsException] is thrown and rerun request after lock time
 */
object ExceptionsOnlyLimiter : RequestLimiter {
    override suspend fun <T> limit(block: suspend () -> T): T {
        var result: Result<T>? = null
        while (result == null || result.isFailure) {
            result = runCatchingSafely {
                block()
            }.onFailure {
                if (it is TooMuchRequestsException) {
                    delay(it.retryAfter.leftToRetry)
                } else {
                    throw it
                }
            }
        }
        return result.getOrThrow()
    }
}
