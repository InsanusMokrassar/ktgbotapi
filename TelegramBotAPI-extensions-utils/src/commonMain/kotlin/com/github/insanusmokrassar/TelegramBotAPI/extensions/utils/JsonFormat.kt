package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils

import kotlinx.serialization.json.Json

@Suppress("EXPERIMENTAL_API_USAGE")
internal val nonstrictJsonFormat = Json {
    isLenient = true
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = true
    encodeDefaults = true
}
