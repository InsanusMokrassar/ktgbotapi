package dev.inmo.tgbotapi.extensions.utils.shortcuts

import dev.inmo.micro_utils.coroutines.replaceIfFailure
import dev.inmo.micro_utils.coroutines.runCatchingLogging
import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.requests.abstracts.Request
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>,
    scope: CoroutineScope
): Deferred<T> = scope.async {
    runCatchingLogging(logger = Log) {
        execute(request)
    }.getOrThrow()
}

suspend fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>
): Deferred<T> = executeAsync(request, CoroutineScope(coroutineContext))

suspend fun <T: Any> RequestsExecutor.executeUnsafe(
    request: Request<T>,
    retries: Int = 0,
    retriesDelay: Long = 1000L,
    onAllFailed: (suspend (exceptions: Array<Throwable>) -> Unit)? = null
): T? {
    var leftRetries = retries
    val exceptions = onAllFailed ?.let { mutableListOf<Throwable>() }
    do {
        return runCatching {
            execute(request)
        }.replaceIfFailure {
            leftRetries--
            delay(retriesDelay)
            exceptions?.add(it)
            null
        }.getOrThrow() ?: continue
    } while(leftRetries >= 0)
    onAllFailed ?.invoke(exceptions ?.toTypedArray() ?: emptyArray())
    return null
}
