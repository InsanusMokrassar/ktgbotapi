package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.shortcuts

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.utils.handleSafely
import kotlinx.coroutines.*

fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>,
    scope: CoroutineScope
): Deferred<T> = scope.async {
    handleSafely {
        execute(request)
    }
}

suspend fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>
): Deferred<T> = coroutineScope {
    executeAsync(request, this)
}

suspend fun <T: Any> RequestsExecutor.executeUnsafe(
    request: Request<T>,
    retries: Int = 0,
    retriesDelay: Long = 1000L,
    onAllFailed: (suspend (exceptions: Array<Exception>) -> Unit)? = null
): T? {
    var leftRetries = retries
    val exceptions = onAllFailed ?.let { mutableListOf<Exception>() }
    do {
        return handleSafely(
            {
                leftRetries--
                delay(retriesDelay)
                exceptions ?.add(it)
                null
            }
        ) {
            execute(request)
        } ?: continue
    } while(leftRetries >= 0)
    onAllFailed ?.invoke(exceptions ?.toTypedArray() ?: emptyArray())
    return null
}
