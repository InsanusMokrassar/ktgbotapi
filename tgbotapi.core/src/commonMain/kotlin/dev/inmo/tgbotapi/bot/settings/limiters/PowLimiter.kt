package dev.inmo.tgbotapi.bot.settings.limiters

import dev.inmo.micro_utils.coroutines.actor
import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.types.MilliSeconds
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.coroutines.*
import kotlin.math.pow

private sealed class RequestEvent

private class AddRequest(
    val continuation: Continuation<MilliSeconds>,
) : RequestEvent()

private object CompleteRequest : RequestEvent()

@Serializable
data class PowLimiter(
    private val minAwaitTime: MilliSeconds = 0L,
    private val maxAwaitTime: MilliSeconds = 10000L,
    private val powValue: Double = 4.0,
    private val powK: Double = 1.6,
    @Transient
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
) : RequestLimiter {
    @Transient
    private val awaitTimeRange = minAwaitTime..maxAwaitTime

    @Transient
    private val eventsChannel = let {
        var requestsInWork = 0.0
        scope.actor<RequestEvent> {
            when (it) {
                is AddRequest -> {
                    val awaitTime = (requestsInWork.pow(powValue) * powK).toLong()
                    requestsInWork++

                    it.continuation.resume(
                        when {
                            awaitTime in awaitTimeRange -> awaitTime
                            awaitTime < awaitTimeRange.first -> awaitTimeRange.first
                            else -> awaitTimeRange.last
                        },
                    )
                }
                is CompleteRequest -> requestsInWork--
            }
        }
    }

    private suspend inline fun <T> withDelay(crossinline block: suspend () -> T): T {
        val delayMillis = suspendCoroutine<Long> {
            scope.launch { eventsChannel.send(AddRequest(it)) }
        }
        delay(delayMillis)
        return try {
            safely { block() }
        } finally {
            eventsChannel.send(CompleteRequest)
        }
    }

    override suspend fun <T> limit(block: suspend () -> T): T {
        return withDelay(block)
    }
}
