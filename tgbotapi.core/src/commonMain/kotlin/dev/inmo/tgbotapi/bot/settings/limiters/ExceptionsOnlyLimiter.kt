package dev.inmo.tgbotapi.bot.settings.limiters

import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.exceptions.TooMuchRequestsException
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.MilliSeconds
import dev.inmo.tgbotapi.types.RetryAfterError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Simple limiter which will lock any request when TooMuchRequestsExceptions is thrown and rerun request after lock time
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
