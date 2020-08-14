package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

/**
 * Analog of [merge] function for [Flow]s. The difference is in the usage of [BroadcastChannel] in this case
 */
fun <T> aggregateFlows(
    withScope: CoroutineScope,
    vararg flows: Flow<T>,
    internalBufferSize: Int = Channel.BUFFERED
): Flow<T> {
    val bc = BroadcastChannel<T>(internalBufferSize)
    flows.forEach {
        it.onEach {
            safely { bc.send(it) }
        }.launchIn(withScope)
    }
    return bc.asFlow()
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
