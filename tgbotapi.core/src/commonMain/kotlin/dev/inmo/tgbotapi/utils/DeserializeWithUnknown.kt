package dev.inmo.tgbotapi.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

fun <T> Decoder.deserializeWithRaw(serializer: KSerializer<T>): Pair<T, JsonElement?> {
    return if (this is JsonDecoder) {
        val json = decodeJsonElement()
        this.json.decodeFromJsonElement(serializer, json) to json
    } else {
        serializer.deserialize(this) to null
    }
}
