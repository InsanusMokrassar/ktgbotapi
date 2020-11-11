package dev.inmo.tgbotapi.bot.settings.limiters

import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.exceptions.TooMuchRequestsException
import dev.inmo.tgbotapi.types.RetryAfterError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

/**
 * This limiter will limit requests only after getting a [RetryAfterError] from incoming [block]s. Important thing is
 * that in case if some of block has been blocked, all the others will wait until it will be possible to be called
 */
object ExceptionsOnlyLimiter : RequestLimiter {
    private val lockState = MutableStateFlow(false)
    override suspend fun <T> limit(block: suspend () -> T): T {
        while (true) {
            lockState.first { !it }
            val result = safely({
                if (it is TooMuchRequestsException) {
                    try {
                        safely {
                            lockState.emit(true)
                            delay(it.retryAfter.leftToRetry)
                        }
                    } finally {
                        lockState.emit(false)
                    }
                    Result.failure(it)
                } else {
                    throw it
                }
            }) {
                Result.success(block())
            }
            if (result.isSuccess) {
                return result.getOrNull()!!
            }
        }
    }
}

@Deprecated("Renamed", ReplaceWith("ExceptionsOnlyLimiter", "dev.inmo.tgbotapi.bot.settings.limiters.ExceptionsOnlyLimiter"))
typealias EmptyLimiter = ExceptionsOnlyLimiter
