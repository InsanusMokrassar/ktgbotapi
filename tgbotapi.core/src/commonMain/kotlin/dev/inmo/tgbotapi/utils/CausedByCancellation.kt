package dev.inmo.tgbotapi.utils

import kotlinx.coroutines.CancellationException

/**
 * Traverses this throwable's cause chain to find a [CancellationException].
 *
 * It walks through `this` and its `cause`s until the first [CancellationException] is encountered,
 * and returns that exception. If no [CancellationException] is found in the chain, returns `null`.
 *
 * This is useful when you need to distinguish cancellations from other failures while handling errors.
 *
 * @receiver the root [Throwable] to inspect
 * @return the first [CancellationException] found in the cause chain, or `null` if none present
 */
fun Throwable.causedCancellationException(): CancellationException? {
    var current = this
    while (current !is CancellationException) {
        when {
            // It is possible, that API will be changed and cancellation will be caused by something else
            current is CancellationException && current.cause == null -> return current
            else -> current = current.cause ?: return null
        }
    }

    return current
}

/**
 * Checks whether this throwable (or any of its causes) is a [CancellationException].
 *
 * @receiver the [Throwable] to inspect
 * @return `true` if a [CancellationException] is found in the cause chain, `false` otherwise
 */
fun Throwable.isCausedByCancellation(): Boolean = causedCancellationException() != null
