package dev.inmo.tgbotapi.bot.ktor.base

import io.ktor.client.*
import io.ktor.client.engine.cio.*

/**
 * This function is used in default constructor of [MultipleClientKtorRequestsExecutor] and on all non-native
 * platforms and MingwX64 should return [client]
 *
 * On LinuxX64 it will create copy with Curl engine or throw an exception if engine is different with Curl
 *
 * @throws IllegalArgumentException When pass non Curl-based [HttpClient] on LinuxX64
 */
internal actual inline fun platformClientCopy(client: HttpClient): HttpClient = (client.engineConfig as? CIOEngineConfig) ?.let {
    lateinit var config: HttpClientConfig<out CIOEngineConfig>
    client.config {
        config = this as HttpClientConfig<out CIOEngineConfig>
    }.close()
    HttpClient(CIO) {
        this.plusAssign(config)
    }
} ?: throw IllegalArgumentException("On LinuxX64 TelegramBotAPI currently support only Curl Ktor HttpClient engine")
