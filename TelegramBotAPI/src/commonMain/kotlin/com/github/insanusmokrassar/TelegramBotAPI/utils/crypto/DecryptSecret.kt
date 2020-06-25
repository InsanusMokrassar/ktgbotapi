package com.github.insanusmokrassar.TelegramBotAPI.utils.crypto

import com.soywiz.krypto.AES
import com.soywiz.krypto.Padding

fun decryptSecret(
    privateKey: ByteArray,
    encryptedSecret: ByteArray
): ByteArray {
    AES.decryptAes128Cbc(privateKey, Padding.PKCS7Padding)
}
