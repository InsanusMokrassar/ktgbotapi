package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor

import com.github.insanusmokrassar.TelegramBotAPI.bot.settings.ProxySettings
import io.ktor.http.HttpHeaders
import okhttp3.Credentials
import okhttp3.OkHttpClient
import java.net.InetSocketAddress
import java.net.Proxy

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
                    HttpHeaders.ProxyAuthorization,
                    Credentials.basic(proxySettings.username ?: "", password)
                )
            }.build()
        }
    }
}
