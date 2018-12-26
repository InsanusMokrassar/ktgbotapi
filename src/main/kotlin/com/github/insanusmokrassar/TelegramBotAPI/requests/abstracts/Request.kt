package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ResponseParameters
import com.github.insanusmokrassar.TelegramBotAPI.utils.*
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
): ResponseParameters<T> {
    return parse(ResponseParameters.serializer(dataSerializer), from)
}