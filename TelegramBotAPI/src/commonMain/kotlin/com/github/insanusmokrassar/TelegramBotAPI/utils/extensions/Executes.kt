package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import com.github.insanusmokrassar.TelegramBotAPI.utils.handleSafely
import kotlinx.coroutines.*


fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>,
    onFail: (suspend (Response) -> Unit)? = null,
    scope: CoroutineScope = GlobalScope,
    onSuccess: (suspend (T) -> Unit)? = null
): Job {
    return scope.launch {
        try {
            val result = execute(request)
            onSuccess ?.invoke(result)
        } catch (e: RequestException) {
            onFail ?.invoke(e.response)
        }
    }
}

fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>,
    scope: CoroutineScope = GlobalScope
): Deferred<T> {
    return scope.async { execute(request) }
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
        handleSafely(
            {
                leftRetries--
                delay(retriesDelay)
                exceptions ?.add(it)
                null
            }
        ) {
            execute(request)
        } ?.let { return it }
    } while(leftRetries >= 0)
    onAllFailed ?.invoke(exceptions ?.toTypedArray() ?: emptyArray())
    return null
}
