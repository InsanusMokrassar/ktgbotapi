package dev.inmo.tgbotapi.bot.settings.limiters

import dev.inmo.tgbotapi.bot.exceptions.TooMuchRequestsException
import dev.inmo.tgbotapi.utils.isCausedUnresolvedAddressException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.delay

/**
 * Simple limiter which will lock any request when [TooMuchRequestsException] is thrown and rerun request after lock time
 */
object ExceptionsOnlyLimiter : RequestLimiter {
    override suspend fun <T> limit(block: suspend () -> T): T {
        var result: Result<T>? = null
        while (result == null || result.isFailure) {
            result = runCatching {
                block()
            }.onFailure {
                when {
                    it.isCausedUnresolvedAddressException() -> delay(1000L)
                    it is TooMuchRequestsException -> delay(it.retryAfter.leftToRetry)
                    else -> throw it
                }
            }
        }
        return result.getOrThrow()
    }
}
