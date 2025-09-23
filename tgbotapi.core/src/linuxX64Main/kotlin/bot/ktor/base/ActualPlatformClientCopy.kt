package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.tgbotapi.utils.defaultKtorEngine
import io.ktor.client.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.curl.*

/**
 * This function is used in default constructor of [MultipleClientKtorRequestsExecutor] and on all non-native
 * platforms and MingwX64 should return [client]
 *
 * On LinuxX64 it will create copy with Curl engine or throw an exception if engine is different with Curl
 *
 * @throws IllegalArgumentException When pass non Curl-based [HttpClient] on LinuxX64
 */
@Suppress("NOTHING_TO_INLINE")
internal actual inline fun platformClientCopy(client: HttpClient): HttpClient = (client.engineConfig as? CurlClientEngineConfig) ?.let {
    lateinit var config: HttpClientConfig<out CurlClientEngineConfig>
    client.config {
        @Suppress("UNCHECKED_CAST")
        config = this as HttpClientConfig<out CurlClientEngineConfig>
    }.close()
    HttpClient(Curl) {
        this.plusAssign(config)
    }
} ?: HttpClient(
        defaultKtorEngine
) {
    install(client)
}
