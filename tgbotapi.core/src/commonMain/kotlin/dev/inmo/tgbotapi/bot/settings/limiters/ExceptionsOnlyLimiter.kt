package dev.inmo.tgbotapi.bot.settings.limiters

import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.exceptions.TooMuchRequestsException
import dev.inmo.tgbotapi.types.MilliSeconds
import dev.inmo.tgbotapi.types.RetryAfterError
import io.ktor.client.features.ClientRequestException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

/**
 * This limiter will limit requests only after getting a [RetryAfterError] or [ClientRequestException] with
 * [HttpStatusCode.TooManyRequests] status code. Important thing is that in case if some of block has been blocked, all
 * the others will wait until it will be possible to be called
 *
 * @param defaultTooManyRequestsDelay This parameter will be used in case of getting [ClientRequestException] with
 * [HttpStatusCode.TooManyRequests] as a parameter for delay like it would be [TooMuchRequestsException]. The reason of
 * it is that in [ClientRequestException] there is no information about required delay between requests
 */
class ExceptionsOnlyLimiter(
    private val defaultTooManyRequestsDelay: MilliSeconds = 1000L
) : RequestLimiter {
    private val lockState = MutableStateFlow(false)
    private suspend fun lock(timeMillis: MilliSeconds) {
        try {
            safely {
                lockState.emit(true)
                delay(timeMillis)
            }
        } finally {
            lockState.emit(false)
        }
    }

    override suspend fun <T> limit(block: suspend () -> T): T {
        while (true) {
            lockState.first { !it }
            var throwable: Throwable? = null
            val result = safely({
                throwable = when (it) {
                    is TooMuchRequestsException -> {
                        lock(it.retryAfter.leftToRetry)
                        it
                    }
                    is ClientRequestException -> {
                        if (it.response.status == HttpStatusCode.TooManyRequests) {
                            lock(defaultTooManyRequestsDelay)
                        } else {
                            throw it
                        }
                        it
                    }
                    else -> throw it
                }
                null
            }) {
                block()
            }
            if (throwable == null) {
                return result!!
            }
        }
    }
}
