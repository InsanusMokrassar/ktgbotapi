package com.github.insanusmokrassar.TelegramBotAPI.types

import java.util.concurrent.TimeUnit

sealed class RequestError

data class RetryAfterError(
    val seconds: Long,
    val startCountingMillis: Long
) : RequestError() {
    val canContinue = TimeUnit.SECONDS.toMillis(seconds) + startCountingMillis
    val leftToRetry: Long
        get() = canContinue - System.currentTimeMillis()
}

data class MigrateChatId(
    val newChatId: ChatId
) : RequestError()


