package dev.inmo.tgbotapi.bot.ktor.middlewares.builtins

import dev.inmo.tgbotapi.bot.ktor.middlewares.TelegramBotMiddleware
import dev.inmo.tgbotapi.requests.abstracts.Request
import korlibs.time.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.reflect.KClass
import kotlin.time.Duration

/**
 * @see invoke
 */
object ExceptionsThrottlerTelegramBotMiddleware {
    const val id: String = "ExceptionsThrottlerTelegramBotMiddleware"

    /**
     * Creates [TelegramBotMiddleware] and configures it with next parameters:
     *
     * * [TelegramBotMiddleware.onRequestException] will throttle after exception if exception has happened before
     * * [TelegramBotMiddleware.onRequestReturnResult] will clear state of all exceptions happened with the [Request] if its
     * handling has been completed successfully
     */
    operator fun invoke(
        exceptionDurationMultiplier: Float = 2f,
        initialExceptionDuration: Duration = 125.milliseconds,
    ): TelegramBotMiddleware =
        TelegramBotMiddleware.build {
            val exceptionsTimeouts = mutableMapOf<KClass<*>, Duration>()
            val latestExceptionsRequestsTypes = mutableMapOf<KClass<*>, MutableSet<KClass<*>>>()
            val mutex = Mutex()
            onRequestException = onRequestException@{ request, t ->
                t ?: return@onRequestException null
                val kclass = t::class
                val toSleep =
                    mutex.withLock {
                        val latestDuration = exceptionsTimeouts[kclass]
                        exceptionsTimeouts[kclass] = latestDuration ?.times(exceptionDurationMultiplier.toDouble()) ?: initialExceptionDuration
                        latestExceptionsRequestsTypes.getOrPut(request::class) { mutableSetOf() }.add(kclass)
                        latestDuration
                    }
                toSleep ?.let {
                    delay(it)
                }
                null
            }
            onRequestReturnResult = onRequestReturnResult@{ result, request, _ ->
                if (result.isSuccess) {
                    mutex.withLock {
                        val exceptionKClass = latestExceptionsRequestsTypes.remove(request::class) ?: return@withLock
                        exceptionKClass.forEach {
                            exceptionsTimeouts.remove(it)
                        }
                    }
                }
                null
            }
            id = ExceptionsThrottlerTelegramBotMiddleware.id
        }
}
