package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.utils.toJsonWithoutNulls
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.JsonObject

interface SimpleRequest<T: Any> : Request<T> {
    val requestSerializer: SerializationStrategy<*>
}

@Suppress("UNCHECKED_CAST")
internal fun <T: Any, K: SimpleRequest<T>> K.json(): JsonObject = toJsonWithoutNulls(requestSerializer as SerializationStrategy<K>)
