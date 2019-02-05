package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import com.github.insanusmokrassar.TelegramBotAPI.utils.toJsonWithoutNulls
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject

interface Request<T: Any> {
    fun method(): String
    fun resultSerializer(): KSerializer<T>
    @ImplicitReflectionSerializer
    fun json(): JsonObject = toJsonWithoutNulls()
}

fun <T : Any> StringFormat.extractResult(
    from: String,
    dataSerializer: KSerializer<T>
): Response<T> {
    return parse(Response.serializer(dataSerializer), from)
}