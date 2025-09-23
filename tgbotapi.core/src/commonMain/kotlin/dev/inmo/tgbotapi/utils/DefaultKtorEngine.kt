package dev.inmo.tgbotapi.utils

import io.ktor.client.engine.HttpClientEngineFactory

expect val defaultKtorEngine: HttpClientEngineFactory<*>
