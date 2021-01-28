package dev.inmo.tgbotapi.utils.passport

import dev.inmo.micro_utils.crypto.decodeBase64
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import javax.crypto.Cipher

private val regexToRemoveFromKey = Regex("(-----(BEGIN|END) ((?:.*? KEY)|CERTIFICATE)-----|[\\s])")

/**
 * @param key PKCS8
 */
class PKCS8Decryptor (key: String): Decryptor {
    private val privateKey: RSAPrivateKey = KeyFactory.getInstance("RSA").generatePrivate(
        PKCS8EncodedKeySpec(key.replace(regexToRemoveFromKey, "").decodeBase64())
    ) as RSAPrivateKey
    private val chunkSize: Int = privateKey.modulus.bitLength() / 8

    override fun ByteArray.decrypt(): ByteArray {
        return Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING").run {
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

fun Decryptor(key: String) = PKCS8Decryptor(key)

inline fun <T> doWithDecryptor(decryptor: Decryptor, crossinline block: Decryptor.() -> T) = decryptor.run(block)
inline fun <T> doWithDecryptor(key: String, crossinline block: Decryptor.() -> T) = doWithDecryptor(Decryptor(key), block)
