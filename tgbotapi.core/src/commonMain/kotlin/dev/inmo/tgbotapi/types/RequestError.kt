package dev.inmo.tgbotapi.types

import korlibs.time.DateTime

sealed class RequestError

data class RetryAfterError(
    val seconds: Seconds,
    val startCountingMillis: Long,
) : RequestError() {
    val canContinue = (seconds * 1000L) + startCountingMillis
    val leftToRetry: Long
        get() = canContinue - DateTime.nowUnixMillisLong()
}

data class MigrateChatId(
    val newChatId: IdChatIdentifier,
) : RequestError()
