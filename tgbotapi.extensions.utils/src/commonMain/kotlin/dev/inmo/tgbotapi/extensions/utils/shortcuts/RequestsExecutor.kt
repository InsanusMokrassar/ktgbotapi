package dev.inmo.tgbotapi.extensions.utils.shortcuts

import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.requests.abstracts.Request
import kotlinx.coroutines.*

fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>,
    scope: CoroutineScope
): Deferred<T> = scope.async {
    safely {
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
    onAllFailed: (suspend (exceptions: Array<Throwable>) -> Unit)? = null
): T? {
    var leftRetries = retries
    val exceptions = onAllFailed ?.let { mutableListOf<Throwable>() }
    do {
        return safely (
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
