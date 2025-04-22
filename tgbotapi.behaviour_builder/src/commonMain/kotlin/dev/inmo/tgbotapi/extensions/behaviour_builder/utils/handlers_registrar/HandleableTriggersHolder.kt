package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar

import dev.inmo.micro_utils.coroutines.runCatchingSafely
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

open class HandleableTriggersHolder<T>(
    preset: List<T> = emptyList(),
) {
    protected val commandsMutex = Mutex()
    protected val handleableCounts = mutableMapOf<T, Int>()
    protected val _handleable =
        mutableListOf<T>().also {
            it.addAll(preset)
        }
    val handleable: List<T>
        get() = _handleable.toList()

    suspend fun registerHandleable(data: T) {
        commandsMutex.withLock {
            _handleable.add(data)
            handleableCounts[data] = (handleableCounts[data] ?: 0) + 1
        }
    }

    suspend fun unregisterHandleable(data: T) {
        commandsMutex.withLock {
            val newHandleableCount = (handleableCounts[data] ?: 0) - 1
            if (newHandleableCount > 0) {
                handleableCounts[data] = newHandleableCount
            } else {
                handleableCounts.remove(data)
                _handleable.remove(data)
            }
        }
    }
}

suspend fun <T, R> HandleableTriggersHolder<T>.doWithRegistration(
    data: T,
    block: suspend () -> R,
): R {
    registerHandleable(data)
    val result =
        runCatchingSafely {
            block()
        }
    unregisterHandleable(data)
    return result.getOrThrow()
}
