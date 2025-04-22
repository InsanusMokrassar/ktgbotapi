package dev.inmo.tgbotapi.utils

import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.*

internal val nonstrictJsonFormat =
    Json {
        isLenient = true
        ignoreUnknownKeys = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = true
        encodeDefaults = true
    }

fun <T : Any> T.toJsonWithoutNulls(serializer: SerializationStrategy<T>): JsonObject = toJson(serializer).withoutNulls()

fun <T : Any> T.toJson(serializer: SerializationStrategy<T>): JsonObject =
    nonstrictJsonFormat.encodeToJsonElement(
        serializer,
        this,
    ).jsonObject

fun JsonArray.withoutNulls(): JsonArray {
    return buildJsonArray {
        forEach {
            when (it) {
                is JsonObject -> add(it.withoutNulls())
                is JsonArray -> add(it.withoutNulls())
                is JsonPrimitive -> add(it)
            }
        }
    }
}

fun JsonObject.withoutNulls(): JsonObject {
    return buildJsonObject {
        forEach { (k, v) ->
            when (v) {
                is JsonObject -> put(k, v.withoutNulls())
                is JsonArray -> put(k, v.withoutNulls())
                !is JsonNull -> put(k, v)
                JsonNull -> {
                    // do nothing
                }
            }
        }
    }
}

fun JsonObject.mapWithCommonValues(): Map<String, Any> =
    map {
            (key, value) ->
        key to
            when (value) {
                is JsonPrimitive -> value.contentOrNull ?: value.booleanOrNull ?: value.doubleOrNull ?: value.floatOrNull ?: value.intOrNull
                is JsonArray, is JsonObject -> value.toString()
                is JsonNull -> null
            }
    }.toMap().mapNotNullValues()
