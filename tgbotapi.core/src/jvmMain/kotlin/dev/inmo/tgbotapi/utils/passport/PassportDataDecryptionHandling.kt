package dev.inmo.tgbotapi.utils.passport

import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.passport.decrypted.SecureData
import java.security.PrivateKey

inline fun <T> PassportData.doInDecryptionContextWithPKCS8Key(
    pkcs8Key: PrivateKey,
    expectedNonce: String? = null,
    crossinline block: SecureData.() -> T
): T {
    val decryptedCredentials = credentials.decryptWithPKCS8PrivateKey(pkcs8Key)
    expectedNonce ?.let { require(expectedNonce == decryptedCredentials.nonce) }
    return decryptedCredentials.secureData.run(block)
}
inline fun <T> PassportData.doInDecryptionContextWithPKCS8Key(
    pkcs8Key: String,
    expectedNonce: String? = null,
    crossinline block: SecureData.() -> T
): T {
    val decryptedCredentials = credentials.decryptWithPKCS8PrivateKey(pkcs8Key)
    expectedNonce ?.let { require(expectedNonce == decryptedCredentials.nonce) }
    return decryptedCredentials.secureData.run(block)
}
