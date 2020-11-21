package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.tgbotapi.utils.*
import kotlinx.coroutines.CoroutineScope

/**
 * Shortcut for [dev.inmo.micro_utils.coroutines.safely]. It was created for more comfortable way of handling different things
 */
@Deprecated("In future will be used typealias from micro_utils", ReplaceWith("safely", "dev.inmo.micro_utils.coroutines.safely"))
suspend inline fun <T> safely(
    noinline onException: ExceptionHandler<T> = { throw it },
    noinline block: suspend CoroutineScope.() -> T
): T = dev.inmo.micro_utils.coroutines.safely(
    onException,
    block
)
