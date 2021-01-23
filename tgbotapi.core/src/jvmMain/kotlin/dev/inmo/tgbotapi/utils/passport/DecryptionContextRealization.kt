package dev.inmo.tgbotapi.utils.passport

import dev.inmo.micro_utils.crypto.decodeBase64
import sun.security.rsa.RSAPublicKeyImpl
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import javax.crypto.Cipher

private val regexToRemoveFromKey = Regex("(-----(BEGIN|END) ((?:.*? KEY)|CERTIFICATE)-----|[\\s])")

private fun String.adaptKey() {
    val replaced = replace(regexToRemoveFromKey, "")

}

/**
 * @param key PKCS8
 */
actual class DecryptionContext actual constructor(key: String) {
    private val privateKey: RSAPrivateKey = KeyFactory.getInstance("RSA").generatePrivate(
        PKCS8EncodedKeySpec(key.replace(regexToRemoveFromKey, "").decodeBase64())
    ) as RSAPrivateKey
    private val chunkSize: Int = privateKey.modulus.bitLength() / 8

    actual fun ByteArray.decrypt(): ByteArray {
        return Cipher.getInstance("RSA/ECB/PKCS1Padding").run {
            init(Cipher.DECRYPT_MODE, privateKey)
            (0 until size step chunkSize).flatMap {
                val firstIndex = it
                val lastIndexExclusive = if (it + chunkSize > size) {
                    size
                } else {
                    it + chunkSize
                }
                doFinal(copyOfRange(firstIndex, lastIndexExclusive)).toList()
            }.toByteArray()
        }
    }
}