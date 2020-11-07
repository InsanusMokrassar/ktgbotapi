package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.micro_utils.coroutines.safely
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.supervisorScope


@Deprecated("In future will be used typealias from micro_utils", ReplaceWith("ExceptionHandler", "dev.inmo.micro_utils.coroutines.ExceptionHandler"))
typealias ExceptionHandler<T> = ExceptionHandler<T>
/**
 * It will run [block] inside of [supervisorScope] to avoid problems with catching of exceptions
 *
 * @param [onException] Will be called when happen exception inside of [block]. By default will throw exception - this
 * exception will be available for catching
 */
@Deprecated("In future will be used typealias from micro_utils", ReplaceWith("safely", "dev.inmo.micro_utils.coroutines.safely"))
suspend inline fun <T> handleSafely(
    noinline onException: ExceptionHandler<T> = { throw it },
    noinline block: suspend CoroutineScope.() -> T
): T = safely(onException, block)
