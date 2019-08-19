package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.utils.toJsonWithoutNulls
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject

@Serializable(RequestSerializer::class)
interface Request<T: Any> {
    fun method(): String
    fun resultDeserializer(): DeserializationStrategy<T>
    fun json(): JsonObject = toJsonWithoutNulls(RequestSerializer)
}
object RequestSerializer : KSerializer<Request<*>> by ContextSerializer(Request::class)
