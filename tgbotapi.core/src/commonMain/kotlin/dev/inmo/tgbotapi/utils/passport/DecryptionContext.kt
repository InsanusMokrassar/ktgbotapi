package dev.inmo.tgbotapi.utils.passport

import dev.inmo.micro_utils.crypto.SourceBytes
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedData

interface Decryptor {
    fun decrypt(data: EncryptedData): SourceBytes
}
