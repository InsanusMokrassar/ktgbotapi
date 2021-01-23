package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.micro_utils.coroutines.safely
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*

/**
 * Analog of [merge] function for [Flow]s. The difference is in the usage of [BroadcastChannel] in this case
 */
fun <T> aggregateFlows(
    withScope: CoroutineScope,
    vararg flows: Flow<T>,
    internalBufferSize: Int = 64
): Flow<T> {
    val sharedFlow = MutableSharedFlow<T>(extraBufferCapacity = internalBufferSize)
    flows.forEach {
        it.onEach {
            safely { sharedFlow.emit(it) }
        }.launchIn(withScope)
    }
    return sharedFlow
}

fun <T> Flow<Iterable<T>>.flatMap(): Flow<T> = flow {
    collect {
        it.forEach {
            emit(it)
        }
    }
}

fun <T, R> Flow<T>.flatMap(mapper: (T) -> Iterable<R>): Flow<R> = flow {
    collect {
        mapper(it).forEach {
            emit(it)
        }
    }
}
