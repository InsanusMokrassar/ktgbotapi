package dev.inmo.tgbotapi.requests.abstracts

import kotlinx.serialization.DeserializationStrategy

interface Request<T : Any> {
    fun method(): String

    val resultDeserializer: DeserializationStrategy<T>
}
