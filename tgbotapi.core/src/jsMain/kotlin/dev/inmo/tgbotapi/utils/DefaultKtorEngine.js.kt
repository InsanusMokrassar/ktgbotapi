package dev.inmo.tgbotapi.utils

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.js.Js

actual val defaultKtorEngine: HttpClientEngineFactory<*> by lazy {
    Js
}
