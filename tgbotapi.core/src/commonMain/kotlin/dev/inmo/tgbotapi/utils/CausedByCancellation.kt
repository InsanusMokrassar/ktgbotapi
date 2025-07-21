package dev.inmo.tgbotapi.utils

import kotlinx.coroutines.CancellationException

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

fun Throwable.isCausedByCancellation(): Boolean = causedCancellationException() == null
