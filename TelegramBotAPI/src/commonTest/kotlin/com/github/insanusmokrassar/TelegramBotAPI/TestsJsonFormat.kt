package com.github.insanusmokrassar.TelegramBotAPI

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

val TestsJsonFormat = Json(JsonConfiguration.Stable)
val NonstrictTestsJsonFormat = Json {
    isLenient = true
    ignoreUnknownKeys = true
    serializeSpecialFloatingPointValues = true
    useArrayPolymorphism = true
}
