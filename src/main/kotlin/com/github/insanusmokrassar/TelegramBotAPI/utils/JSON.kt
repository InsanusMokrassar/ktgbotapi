package com.github.insanusmokrassar.TelegramBotAPI.utils

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.*

@Deprecated("This method can throw exceptions")
@ImplicitReflectionSerializer
inline fun <reified T: Any> T.toJsonWithoutNulls(): JsonObject = Json.nonstrict.toJson(
    this
).jsonObject.withoutNulls()

inline fun <reified T: Any> T.toJsonWithoutNulls(serializer: KSerializer<T>): JsonObject = toJson(serializer).withoutNulls()

inline fun <reified T: Any> T.toJson(serializer: KSerializer<T>): JsonObject = Json.nonstrict.toJson(
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
