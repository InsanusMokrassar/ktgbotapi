package dev.inmo.tgbotapi.bot.ktor.base

import io.ktor.client.*
import io.ktor.client.engine.curl.*

/**
 * This function is used in default constructor of [MultipleClientKtorRequestsExecutor] and on all non-native
 * platforms should return [client]
 *
 * On LinuxX64 it will create copy with Curl engine or throw an exception if engine is different with Curl
 * On MingwX64 it will create copy with WinHttp engine or throw an exception if engine is different with WinHttp
 *
 * @throws IllegalArgumentException When pass non Curl-based [HttpClient] on LinuxX64 or non WinHttp-based [HttpClient]
 * on MingwX64
 */
internal actual inline fun platformClientCopy(client: HttpClient): HttpClient = (client.engineConfig as? CurlClientEngineConfig) ?.let {
    lateinit var config: HttpClientConfig<out CurlClientEngineConfig>
    client.config {
        config = this as HttpClientConfig<out CurlClientEngineConfig>
    }.close()
    HttpClient(Curl) {
        this.plusAssign(config)
    }
} ?: throw IllegalArgumentException("On LinuxX64 TelegramBotAPI currently support only Curl Ktor HttpClient engine")
