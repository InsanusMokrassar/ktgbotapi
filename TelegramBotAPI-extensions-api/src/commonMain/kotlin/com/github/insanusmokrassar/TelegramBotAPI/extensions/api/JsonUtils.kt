package com.github.insanusmokrassar.TelegramBotAPI.extensions.api

import kotlinx.serialization.json.Json

@Suppress("EXPERIMENTAL_API_USAGE")
internal val nonstrictJsonFormat = Json {
    isLenient = true
    ignoreUnknownKeys = true
    serializeSpecialFloatingPointValues = true
    useArrayPolymorphism = true
}
