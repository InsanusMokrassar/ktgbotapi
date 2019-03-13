package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import com.github.insanusmokrassar.TelegramBotAPI.utils.toJsonWithoutNulls
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject

@Serializable(RequestSerializer::class)
interface Request<T: Any> {
    fun method(): String
    fun resultSerializer(): KSerializer<T>
    fun json(): JsonObject = toJsonWithoutNulls(RequestSerializer)
}
object RequestSerializer : KSerializer<Request<*>> by ContextSerializer(Request::class)

fun <T : Any> StringFormat.extractResult(
    from: String,
    dataSerializer: KSerializer<T>
): Response<T> {
    return parse(Response.serializer(dataSerializer), from)
}