package dev.inmo.tgbotapi.utils

fun <K, V> mapOfNotNull(vararg pairs: Pair<K, V?>): Map<K, V> {
    return HashMap<K, V>().apply {
        pairs.forEach {
                (key, value) ->
            value ?.also {
                put(key, it)
            }
        }
    }
}

fun <K, V> Map<K, V?>.mapNotNullValues(): Map<K, V> =
    asSequence().mapNotNull {
        it.value ?.let { value ->
            it.key to value
        }
    }.toMap()
