package dev.inmo.tgbotapi.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.supervisorScope


typealias ExceptionHandler<T> = suspend (Throwable) -> T
/**
 * It will run [block] inside of [supervisorScope] to avoid problems with catching of exceptions
 *
 * @param [onException] Will be called when happen exception inside of [block]. By default will throw exception - this
 * exception will be available for catching
 */
suspend inline fun <T> handleSafely(
    noinline onException: ExceptionHandler<T> = { throw it },
    noinline block: suspend CoroutineScope.() -> T
): T {
    return try {
        supervisorScope(block)
    } catch (e: Throwable) {
        onException(e)
    }
}
