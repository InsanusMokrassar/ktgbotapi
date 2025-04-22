package dev.inmo.tgbotapi.utils.passport

import dev.inmo.micro_utils.crypto.SourceBytes
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedData
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESDecryptor(key: SourceBytes, private val iv: ByteArray) : Decryptor {
    private val key = SecretKeySpec(key, "AES")

    override fun decrypt(data: EncryptedData): SourceBytes {
        return Cipher.getInstance("AES/CBC/NOPADDING").run {
            init(Cipher.DECRYPT_MODE, key, IvParameterSpec(this@AESDecryptor.iv))
            val decryptedCredentials = doFinal(data)

            val padding = decryptedCredentials.first()
            decryptedCredentials.copyOfRange(padding.toInt(), decryptedCredentials.size)
        }
    }
}
