package com.github.insanusmokrassar.TelegramBotAPI.types

import com.soywiz.klock.DateTime

sealed class RequestError

data class RetryAfterError(
    val seconds: Long,
    val startCountingMillis: Long
) : RequestError() {
    val canContinue = DateTime(seconds).milliseconds + startCountingMillis
    val leftToRetry: Long
        get() = canContinue - DateTime.now().milliseconds
}

data class MigrateChatId(
    val newChatId: ChatId
) : RequestError()


