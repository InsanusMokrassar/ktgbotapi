package dev.inmo.tgbotapi.utils.passport

import dev.inmo.micro_utils.crypto.SourceBytes
import dev.inmo.tgbotapi.types.passport.credentials.EndDataCredentials
import java.security.MessageDigest

fun Pair<SourceBytes, SourceBytes>.createDecryptor(): Decryptor {
    val secretHash = MessageDigest.getInstance("SHA-512").digest(first + second)
    val key = secretHash.copyOf(32)
    val iv = secretHash.copyOfRange(32, 48)

    return AESDecryptor(key, iv)
}

fun EndDataCredentials.createDecryptor() = (secret to hash).createDecryptor()
