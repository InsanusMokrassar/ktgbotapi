package com.github.insanusmokrassar.TelegramBotAPI.utils.crypto

import com.soywiz.krypto.AES
import com.soywiz.krypto.Padding

typealias Decryptor = (ByteArray) -> ByteArray

fun createDecryptor(
    secret: ByteArray,
    hash: ByteArray
): Decryptor {
    val secretHash = sha512(secret + hash)
    val key = secretHash.copyOfRange(0, 32)
    val iv = secretHash.copyOfRange(32, 48)

    return {
        val decrypted = AES.decryptAesCbc(it, key, iv, Padding.NoPadding)
        decrypted.copyOfRange(decrypted[0].toInt(), decrypted.size)
    }
}
