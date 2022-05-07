package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

open class HandleableTriggersHolder<T>(
    preset: List<T> = emptyList()
) {
    protected val commandsMutex = Mutex()
    protected val _handleable = mutableListOf<T>().also {
        it.addAll(preset)
    }
    val handleable: List<T>
        get() = _handleable.toList()

    suspend fun registerHandleable(data: T) {
        commandsMutex.withLock {
            _handleable.add(data)
        }
    }

    suspend fun unregisterHandleable(data: T) {
        commandsMutex.withLock {
            _handleable.remove(data)
        }
    }
}
