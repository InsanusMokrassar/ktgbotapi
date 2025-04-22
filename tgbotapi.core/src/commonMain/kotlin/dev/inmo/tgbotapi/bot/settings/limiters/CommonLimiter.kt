package dev.inmo.tgbotapi.bot.settings.limiters

import dev.inmo.tgbotapi.types.MilliSeconds
import korlibs.time.DateTime
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Semaphore
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.math.roundToLong

private fun now(): Long = DateTime.nowUnixMillisLong()

@Serializable
class CommonLimiter(
    private val lockCount: Int = 10,
    private val regenTime: MilliSeconds = 15 * 1000, // 15 seconds for full regen of opportunity to send message
    @Transient
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
) : RequestLimiter {
    @Transient
    private val quotaSemaphore = Semaphore(lockCount)

    @Transient
    private val counterRegeneratorJob =
        scope.launch {
            val regenDelay: MilliSeconds = (regenTime.toDouble() / lockCount).roundToLong()
            while (isActive) {
                delay(regenDelay)
                if (quotaSemaphore.availablePermits < lockCount) {
                    try {
                        quotaSemaphore.release()
                    } catch (_: IllegalStateException) {
                        // Skip IllegalStateException due to the fact that this exception may happens in release method
                    }
                }
            }
        }

    override suspend fun <T> limit(block: suspend () -> T): T {
        quotaSemaphore.acquire()
        return block()
    }
}
