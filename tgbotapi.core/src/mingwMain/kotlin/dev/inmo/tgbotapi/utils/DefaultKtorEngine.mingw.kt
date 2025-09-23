package dev.inmo.tgbotapi.utils

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.winhttp.WinHttp

actual val defaultKtorEngine: HttpClientEngineFactory<*> by lazy {
    WinHttp
}
