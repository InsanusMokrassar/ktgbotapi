package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils

import com.github.insanusmokrassar.TelegramBotAPI.utils.*
import kotlinx.coroutines.CoroutineScope

/**
 * Shortcut for [handleSafely]. It was created for more comfortable way of handling different things
 */
@PreviewFeature
suspend inline fun <T> safely(
    noinline onException: ExceptionHandler<T> = { throw it },
    noinline block: suspend CoroutineScope.() -> T
): T = handleSafely(
    onException,
    block
)
