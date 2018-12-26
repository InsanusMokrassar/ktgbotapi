package com.github.insanusmokrassar.TelegramBotAPI.bot

import okhttp3.Credentials
import okhttp3.OkHttpClient
import java.net.InetSocketAddress
import java.net.Proxy

data class ProxySettings(
    val host: String = "localhost",
    val port: Int = 1080,
    val username: String? = null,
    val password: String? = null
)

fun OkHttpClient.Builder.useWith(proxySettings: ProxySettings) {
    proxy(
        Proxy(
            Proxy.Type.SOCKS,
            InetSocketAddress(
                proxySettings.host,
                proxySettings.port
            )
        )
    )
    proxySettings.password ?.let {
        password ->
        proxyAuthenticator {
            _, response ->
            response.request().newBuilder().apply {
                addHeader(
                    "Proxy-Authorization",
                    Credentials.basic(proxySettings.username ?: "", password)
                )
            }.build()
        }
    }
}
