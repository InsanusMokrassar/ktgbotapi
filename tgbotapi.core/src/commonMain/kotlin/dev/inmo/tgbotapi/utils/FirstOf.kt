package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.coroutines.firstOf
import kotlinx.coroutines.CoroutineScope

/**
 * Launches all provided suspending [deferreds] in this [CoroutineScope] and returns the value
 * produced by the first block that completes.
 *
 * - Provide at least one block; otherwise the call will never complete.
 * - Cancellation and error propagation semantics are delegated to the underlying
 *   dev.inmo.micro_utils.coroutines.firstOf implementation.
 *
 * @param T The type of the resulting value.
 * @param deferreds The suspending blocks to race; they are started eagerly.
 * @return The result produced by the first completed block.
 */
suspend fun <T> CoroutineScope.firstOf(
    vararg deferreds: suspend () -> T
): T = firstOf {
    deferreds.forEach {
        add {
            it()
        }
    }
}
