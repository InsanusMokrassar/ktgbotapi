package dev.inmo.tgbotapi.utils

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.java.Java

actual val defaultKtorEngine: HttpClientEngineFactory<*> by lazy {
    Java
}
