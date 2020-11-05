package dev.inmo.tgbotapi.utils.extensions

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

private sealed class DebounceAction<T> {
    abstract val value: T
}

private data class AddValue<T>(override val value: T) : DebounceAction<T>()
private data class RemoveJob<T>(override val value: T, val job: Job) : DebounceAction<T>()

typealias AccumulatedValues<K, V> = Pair<K, List<V>>

fun <K, V> ReceiveChannel<Pair<K, V>>.accumulateByKey(
    delayMillis: Long,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    resultBroadcastChannelCapacity: Int = 32
): ReceiveChannel<AccumulatedValues<K, V>> {
    val outChannel = Channel<AccumulatedValues<K, V>>(resultBroadcastChannelCapacity)
    val values = HashMap<K, MutableList<V>>()
    val jobs = HashMap<K, Job>()

    val channel = Channel<DebounceAction<Pair<K, V>>>(Channel.UNLIMITED)
    scope.launch {
        for (action in channel) {
            val (key, value) = action.value
            when (action) {
                is AddValue -> {
                    jobs[key] ?.cancel()
                    (values[key] ?: mutableListOf<V>().also { values[key] = it }).add(value)
                    lateinit var job: Job
                    job = launch {
                        delay(delayMillis)

                        values[key] ?.let {
                            outChannel.send(key to it)
                            channel.send(RemoveJob(key to value, job))
                        }
                    }
                    jobs[key] = job
                }
                is RemoveJob -> if (values[key] == action.job) {
                    values.remove(key)
                    jobs.remove(key)
                }
            }

        }
    }

    scope.launch {
        for (msg in this@accumulateByKey) {
            channel.send(AddValue(msg))
        }
    }

    return outChannel
}
