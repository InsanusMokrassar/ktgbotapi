package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import kotlinx.serialization.DeserializationStrategy

interface Request<T: Any> {
    fun method(): String
    val resultDeserializer: DeserializationStrategy<T>
}
