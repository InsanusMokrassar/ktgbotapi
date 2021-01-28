package dev.inmo.tgbotapi.utils.passport

import dev.inmo.micro_utils.crypto.decodeBase64
import dev.inmo.tgbotapi.types.passport.credentials.DecryptedCredentials
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedCredentials
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

private val regexToRemoveFromKey = Regex("(-----(BEGIN|END) ((?:.*? KEY)|CERTIFICATE)-----|[\\s])")

fun EncryptedCredentials.decryptWithPKCS8PrivateKey(privateKey: PrivateKey): DecryptedCredentials {
    val decrypted = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding").run {
        init(Cipher.DECRYPT_MODE, privateKey)
        doFinal(secret)
    }
    val dataDecryptor = (decrypted to hash).createDecryptor()
    val decryptedCredentials = dataDecryptor.decrypt(data).decodeToString()
    return nonstrictJsonFormat.decodeFromString(DecryptedCredentials.serializer(), decryptedCredentials)
}

fun EncryptedCredentials.decryptWithPKCS8PrivateKey(key: String) = decryptWithPKCS8PrivateKey(
    KeyFactory.getInstance("RSA").generatePrivate(
        PKCS8EncodedKeySpec(key.replace(regexToRemoveFromKey, "").decodeBase64())
    )
)
