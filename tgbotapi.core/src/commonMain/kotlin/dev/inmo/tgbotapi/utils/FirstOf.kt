package dev.inmo.tgbotapi.utils

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlin.coroutines.cancellation.CancellationException

/**
 * Executes the given suspend producers in parallel and returns the first successfully produced value, or null
 * if none of them produce a value.
 *
 * Behaviour:
 * - All [deferreds] are started concurrently.
 * - Failures are ignored except [CancellationException], which is rethrown.
 * - As soon as the first value is emitted, upstream flows are cancelled.
 *
 * Notes:
 * - If every producer fails (without throwing [CancellationException]) or none emits a value, this returns null.
 *
 * @param T the type of the produced value
 * @param deferreds suspend producers that are started in parallel
 * @return the first successfully produced value, or null if none produced a value
 * @throws CancellationException if the coroutine scope is cancelled or any producer throws it
 */
suspend fun <T> firstOfOrNull(
    vararg deferreds: suspend () -> T
): T? {
    val resultFlow = deferreds.map {
        flow {
            runCatching {
                it()
            }.onSuccess {
                emit(it)
            }.onFailure {
                if (it is CancellationException) throw it
            }
        }
    }.merge()
    return resultFlow.firstOrNull()
}

/**
 * Executes the given suspend producers in parallel and returns the first successfully produced value.
 *
 * This is a non-nullable variant of [firstOfOrNull]. If no producer yields a value, it throws an [IllegalStateException].
 *
 * @param T the type of the produced value
 * @param deferreds suspend producers that are started in parallel
 * @return the first successfully produced value
 * @throws IllegalStateException if none of the producers yielded a value
 * @throws CancellationException if the coroutine scope is cancelled or any producer throws it
 */
suspend fun <T> firstOf(
    vararg deferreds: suspend () -> T
): T {
    return firstOfOrNull(*deferreds) ?: error("Unable to get result of deferreds")
}
