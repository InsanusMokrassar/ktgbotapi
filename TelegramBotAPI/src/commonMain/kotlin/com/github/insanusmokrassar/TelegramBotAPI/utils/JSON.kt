package com.github.insanusmokrassar.TelegramBotAPI.utils

import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.*

@Suppress("EXPERIMENTAL_API_USAGE")
internal val nonstrictJsonFormat = Json {
    isLenient = true
    ignoreUnknownKeys = true
    serializeSpecialFloatingPointValues = true
    useArrayPolymorphism = true
}

fun <T: Any> T.toJsonWithoutNulls(serializer: SerializationStrategy<T>): JsonObject = toJson(serializer).withoutNulls()

fun <T: Any> T.toJson(serializer: SerializationStrategy<T>): JsonObject = nonstrictJsonFormat.toJson(
    serializer,
    this
).jsonObject

fun JsonArray.withoutNulls(): JsonArray {
    return jsonArray {
        forEach {
            when (it) {
                is JsonObject -> +it.withoutNulls()
                is JsonArray -> +it.withoutNulls()
                is JsonPrimitive -> +it
            }
        }
    }
}

fun JsonObject.withoutNulls(): JsonObject {
    return json {
        forEach { (k, v) ->
            when (v) {
                is JsonObject -> k to v.withoutNulls()
                is JsonArray -> k to v.withoutNulls()
                !is JsonNull -> k to v
            }
        }
    }
}

fun JsonObject.mapWithCommonValues(): Map<String, Any> = map {
    (key, value) ->
    key to when (value) {
        is JsonPrimitive -> value.contentOrNull ?: value.booleanOrNull ?: value.doubleOrNull ?: value.floatOrNull ?: value.intOrNull
        is JsonArray, is JsonObject -> value.toString()
        is JsonNull -> null
    }
}.toMap().mapNotNullValues()
