package dev.inmo.tgbotapi.utils

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

fun <T> Decoder.extractDataAndJson(serializer: DeserializationStrategy<T>): Pair<T, JsonElement?> {
    return if (this is JsonDecoder) {
        val raw = decodeJsonElement()
        json.decodeFromJsonElement(serializer, raw) to raw
    } else {
        serializer.deserialize(this) to null
    }
}
