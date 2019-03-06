package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import kotlinx.coroutines.*


fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>,
    onFail: (suspend (Response<*>) -> Unit)? = null,
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
    retriesDelay: Long = 1000L
): T? {
    var leftRetries = retries
    while(true) {
        try {
            return execute(request)
        } catch (e: RequestException) {
            if (leftRetries > 0) {
                leftRetries--
                delay(retriesDelay)
            } else {
                return null
            }
        }
    }
}
