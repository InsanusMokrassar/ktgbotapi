package dev.inmo.tgbotapi.updateshandlers.webhook

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.io.FileInputStream
import java.security.KeyStore

@Serializable
data class WebhookPrivateKeyConfig(
    private val keyStorePath: String,
    private val keyStorePassword: String,
    val aliasName: String,
    private val aliasPassword: String,
) {
    @Transient
    val keyStore =
        KeyStore.getInstance("JKS").apply {
            load(FileInputStream(keyStorePath), keyStorePassword())
        }

    fun keyStorePassword(): CharArray = keyStorePassword.toCharArray()

    fun aliasPassword(): CharArray = aliasPassword.toCharArray()
}
