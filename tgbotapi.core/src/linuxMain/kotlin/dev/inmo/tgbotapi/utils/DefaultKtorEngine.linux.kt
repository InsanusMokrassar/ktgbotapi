package dev.inmo.tgbotapi.utils

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.curl.Curl

actual val defaultKtorEngine: HttpClientEngineFactory<*> by lazy {
    Curl
}
