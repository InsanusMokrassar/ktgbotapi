package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.tgbotapi.utils.toJsonWithoutNulls
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.JsonObject

interface SimpleRequest<T : Any> : Request<T> {
    val requestSerializer: SerializationStrategy<*>
}

@Suppress("UNCHECKED_CAST")
internal fun <T : Any, K : SimpleRequest<T>> K.json(): JsonObject = toJsonWithoutNulls(requestSerializer as SerializationStrategy<K>)
