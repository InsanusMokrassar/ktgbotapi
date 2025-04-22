package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.common.Either
import dev.inmo.micro_utils.common.EitherFirst
import dev.inmo.micro_utils.common.EitherSecond
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

fun <T> Decoder.deserializeEitherWithRaw(serializer: KSerializer<T>): Either<Pair<T?, JsonElement>, Pair<T, JsonElement?>> {
    return if (this is JsonDecoder) {
        val json = decodeJsonElement()
        EitherFirst(
            runCatching {
                this.json.decodeFromJsonElement(serializer, json)
            }.getOrNull() to json,
        )
    } else {
        EitherSecond(
            serializer.deserialize(this) to null,
        )
    }
}
