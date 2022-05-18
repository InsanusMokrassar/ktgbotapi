package dev.inmo.tgbotapi.utils

import com.soywiz.krypto.*
import io.ktor.http.decodeURLQueryComponent
import io.ktor.utils.io.core.toByteArray

const val telegramBotAPIDefaultUrl = "https://api.telegram.org"

private inline val String.withoutLastSlash: String
    get() {
        var correctedUrl = this
        while (true) {
            val withoutSuffix = correctedUrl.removeSuffix("/")
            if (withoutSuffix == correctedUrl) {
                return correctedUrl
            }
            correctedUrl = withoutSuffix
        }
    }

class TelegramAPIUrlsKeeper(
    token: String,
    hostUrl: String = telegramBotAPIDefaultUrl,
    urlsSuffixes: String = ""
) {
    val webAppDataSecretKeyHash by lazy {
        HMAC.hmacSHA256("WebAppData".toByteArray(), token.toByteArray())
    }
    val webAppDataSecretKey
        get() = webAppDataSecretKeyHash.hexLower

    val commonAPIUrl: String
    val fileBaseUrl: String

    constructor(token: String, testServer: Boolean, hostUrl: String = telegramBotAPIDefaultUrl) : this(
        token,
        hostUrl,
        "/test".takeIf { testServer } ?: ""
    )

    init {
        val correctedHost = hostUrl.withoutLastSlash
        commonAPIUrl = "$correctedHost/bot$token$urlsSuffixes"
        fileBaseUrl = "$correctedHost/file/bot$token$urlsSuffixes"
    }

    fun createFileLinkUrl(filePath: String) = "${fileBaseUrl}/$filePath"

    /**
     * @param rawData Data from [dev.inmo.tgbotapi.webapps.WebApp.initData]
     * @param hash Data from [dev.inmo.tgbotapi.webapps.WebApp.initDataUnsafe] from the field [dev.inmo.tgbotapi.webapps.WebAppInitData.hash]
     */
    fun checkWebAppData(rawData: String, hash: String): Boolean {
        val preparedData = rawData
            .decodeURLQueryComponent()
            .split("&")
            .filterNot { it.startsWith("hash=") }
            .sorted()
            .joinToString("\n")

        return HMAC.hmacSHA256(webAppDataSecretKeyHash.bytes, preparedData.toByteArray()).hexLower == hash.lowercase()
    }

    /**
     * @param rawData Data from [dev.inmo.tgbotapi.webapps.WebApp.initData]
     * @param hash Data from [dev.inmo.tgbotapi.webapps.WebApp.initDataUnsafe] from the field [dev.inmo.tgbotapi.webapps.WebAppInitData.hash]
     */
    @Deprecated("Renamed", ReplaceWith("checkWebAppData"))
    inline fun checkWebAppLink(rawData: String, hash: String) = checkWebAppData(rawData, hash)
}
