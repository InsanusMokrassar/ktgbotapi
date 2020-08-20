package com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters

import com.soywiz.klock.DateTime
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

private fun now(): Long = DateTime.nowUnixLong()

class CommonLimiter(
    private val lockCount: Int = 10,
    private val regenTime: Long = 20 * 1000L // 20 seconds for full regen of opportunity to send message
) : RequestLimiter {
    private var doLimit: Boolean = false

    private val counterChannel = Channel<Unit>(Channel.UNLIMITED)
    private val scope = CoroutineScope(Dispatchers.Default)
    private val counterJob = scope.launch {
        var wasLastSecond = 0
        var lastCountTime = now()
        var limitManagementJob: Job? = null
        var removeLimitTime: Long = lastCountTime
        for (counter in counterChannel) {
            val now = now()
            if (now - lastCountTime > 1000) {
                lastCountTime = now
                wasLastSecond = 1
            } else {
                wasLastSecond++
            }
            if (wasLastSecond >= lockCount) {
                removeLimitTime = now + regenTime
                if (limitManagementJob == null) {
                    limitManagementJob = launch {
                        doLimit = true
                        var internalNow = now()
                        while (internalNow < removeLimitTime) {
                            delay(removeLimitTime - internalNow)
                            internalNow = now()
                        }
                        doLimit = false
                    }
                }
            }
            if (now > removeLimitTime) {
                limitManagementJob = null
            }
        }
    }

    private val quoterChannel = Channel<Unit>(Channel.CONFLATED)
    private val tickerJob = scope.launch {
        while (isActive) {
            quoterChannel.send(Unit)
            delay(1000L)
        }
    }

    override suspend fun <T> limit(block: suspend () -> T): T {
        counterChannel.send(Unit)
        return if (!doLimit) {
            block()
        } else {
            quoterChannel.receive()
            block()
        }
    }
}
