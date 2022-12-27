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
 * This limiter will limit requests only after getting a [RetryAfterError] or [ClientRequestException] with
 * [HttpStatusCode.TooManyRequests] status code. When block throws [TooMuchRequestsException] or [RetryAfterError],
 * in the limiter will be created special [Mutex] for the key, defined in [requestKeyFactory], and this mutex will be
 * locked for some time based on type of error. See [limit] for more info
 *
 * @param defaultTooManyRequestsDelay This parameter will be used in case of getting [ClientRequestException] with
 * [HttpStatusCode.TooManyRequests] as a parameter for delay like it would be [TooMuchRequestsException]. The reason of
 * it is that in [ClientRequestException] there is no information about required delay between requests
 * @param requestKeyFactory This parameter define how to determine request key in limiter
 */
class ExceptionsOnlyLimiter(
    private val defaultTooManyRequestsDelay: MilliSeconds = 1000L,
) : RequestLimiter {
    /**
     * Should be used for all [mutexesMap] changes
     */
    private val lockMutex = Mutex()

    /**
     * Contains [Mutex]es for [Any] keys. If [Mutex] is presented it means that [lock] function has been called and
     * that mutex should be locked for some time
     */
    private val mutexesMap = mutableMapOf<Any, Mutex>()
    private suspend fun lock(
        key: Any,
        timeMillis: MilliSeconds
    ) {
        val mutex = Mutex()
        mutex.withLock {
            safely {
                lockMutex.withLock {
                    mutexesMap[key] = mutex
                }
                delay(timeMillis)
                lockMutex.withLock {
                    mutexesMap.remove(key)
                }
            }
        }
    }

    /**
     * Just call [block]
     */
    override suspend fun <T> limit(block: suspend () -> T): T {
        try {
            block()
        } catch (e: TooMuchRequestsException) {
            delay(e.retryAfter.leftToRetry)
            limit(block)
        }
    }
}
